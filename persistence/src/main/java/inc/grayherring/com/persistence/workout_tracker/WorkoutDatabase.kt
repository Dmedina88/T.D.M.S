package inc.grayherring.com.persistence.workout_tracker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import inc.grayherring.com.persistence.util.Converters
import inc.grayherring.com.persistence.workout_tracker.models.ExerciseSet
import inc.grayherring.com.persistence.workout_tracker.models.Workout
import inc.grayherring.com.persistence.workout_tracker.models.WorkoutExercises

@Database(entities = [Workout::class, ExerciseSet::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class WorkoutDatabase : RoomDatabase() {
  abstract fun workoutDao(): WorkoutDao

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
          "workout_db"
        ).build()
        INSTANCE = instance
        instance
      }
    }
  }
}