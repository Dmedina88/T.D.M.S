package inc.grayherring.com.persistence.poop_tracker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import inc.grayherring.com.persistence.models.PoopEntry
import inc.grayherring.com.persistence.util.Converters
import inc.grayherring.com.persistence.workout_tracker.WorkoutDatabase

@Database(entities = [PoopEntry::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class EntryDatabase : RoomDatabase() {
  abstract fun entryDao(): EntryDao

  companion object {
    @Volatile
    private var INSTANCE: WorkoutDatabase? = null

    fun getDatabase(context: Context): WorkoutDatabase {
      return INSTANCE
        ?: synchronized(this) {
        // Create database here
        val instance = Room.databaseBuilder(
          context.applicationContext,
          WorkoutDatabase::class.java,
          "Entry_log_db"
        ).build()
        INSTANCE = instance
        instance
      }
    }
  }
}