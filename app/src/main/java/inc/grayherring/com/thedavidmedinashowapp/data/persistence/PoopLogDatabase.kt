package inc.grayherring.com.thedavidmedinashowapp.data.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import inc.grayherring.com.thedavidmedinashowapp.data.models.PoopLog
import inc.grayherring.com.thedavidmedinashowapp.util.Converters

@Database(entities = [PoopLog::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PoopLogDatabase : RoomDatabase() {
  abstract fun poopDao(): PoopLogDao

  companion object {
    @Volatile
    private var INSTANCE: PoopLogDatabase? = null

    fun getDatabase(context: Context): PoopLogDatabase {
      return INSTANCE
        ?: synchronized(this) {
        // Create database here
        val instance = Room.databaseBuilder(
          context.applicationContext,
          PoopLogDatabase::class.java,
          "Poop_log_db"
        ).build()
        INSTANCE = instance
        instance
      }
    }
  }
}