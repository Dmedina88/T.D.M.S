package inc.grayherring.com.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import inc.grayherring.com.persistence.models.DBEntry
import inc.grayherring.com.persistence.util.Converters

@Database(entities = [DBEntry::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class EntryDatabase : RoomDatabase() {
  abstract fun entryDao(): EntryDao

  companion object {
    @Volatile
    private var INSTANCE: EntryDatabase? = null

    fun getDatabase(context: Context): EntryDatabase {
      return INSTANCE
        ?: synchronized(this) {
        // Create database here
        val instance = Room.databaseBuilder(
          context.applicationContext,
          EntryDatabase::class.java,
          "Entry_log_db"
        ).build()
        INSTANCE = instance
        instance
      }
    }
  }
}