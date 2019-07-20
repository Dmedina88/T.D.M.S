package inc.grayherring.com.persistence.workout_tracker

import androidx.lifecycle.LiveData
import inc.grayherring.com.persistence.workout_tracker.models.Workout
import inc.grayherring.com.persistence.workout_tracker.models.WorkoutExercises

interface WorkoutDatastore  {
  fun monitorWorkout(workoutId: Long): LiveData<WorkoutExercises>

  suspend fun insertWorkout(workout: Workout)


  suspend fun deleteEntryLog(workoutExercises: WorkoutExercises)

  fun getFromTable(dayst: Long, dayet: Long): LiveData<List<Workout>>


}

internal class WorkoutDatastoreImpl(private val workoutDao: WorkoutDao) :
  WorkoutDatastore {
  override fun monitorWorkout(workoutId: Long) = workoutDao.monitorWorkout(workoutId)

  override suspend fun insertWorkout(workout: Workout) = workoutDao.insertWorkout(workout)

  override suspend fun deleteEntryLog(workoutExercises: WorkoutExercises) = Unit

  override fun getFromTable(dayst: Long, dayet: Long) = workoutDao.getFromTable(dayst, dayet)
}