package inc.grayherring.com.persistence.workout_tracker

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import inc.grayherring.com.persistence.workout_tracker.models.ExerciseSet
import inc.grayherring.com.persistence.workout_tracker.models.Workout
import inc.grayherring.com.persistence.workout_tracker.models.WorkoutExercises

interface WorkoutDatastore  {
  fun monitorWorkout(workoutId: Long): LiveData<WorkoutExercises>

  suspend fun insertWorkout(workout: Workout)

  fun getFromTable(dayst: Long, dayet: Long): LiveData<List<Workout>>

  suspend fun insertExerciseSet(workoutSet: ExerciseSet)

  suspend fun insertExerciseSets(workoutSets: List<ExerciseSet> )

  suspend fun deleteExerciseSet(workoutSet: ExerciseSet)

  suspend fun deleteWorkout(workout: Workout)


}

internal class WorkoutDatastoreImpl(private val workoutDao: WorkoutDao) :
  WorkoutDatastore by workoutDao