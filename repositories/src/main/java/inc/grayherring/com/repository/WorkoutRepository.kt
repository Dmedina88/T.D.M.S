package inc.grayherring.com.repository;

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import inc.grayherring.com.core.models.ExerciseSet
import inc.grayherring.com.core.models.Workout
import inc.grayherring.com.core.models.WorkoutWithExercises
import inc.grayherring.com.persistence.workout_tracker.WorkoutDatastore
import inc.grayherring.com.repository.util.asDBExerciseSet
import inc.grayherring.com.repository.util.asDBWorkout
import inc.grayherring.com.repository.util.asWorkOut
import inc.grayherring.com.repository.util.asWorkoutWithExercises

interface WorkoutRepository  {
  fun monitorWorkout(workoutId: Long): LiveData<WorkoutWithExercises>

  suspend fun insertWorkout(workout: Workout)

  fun getWorkoutBetweenDates(dayst: Long, dayet: Long): LiveData<List<Workout>>

  suspend fun insertExerciseSet(workoutSet: ExerciseSet)

  suspend fun insertExerciseSets(workoutSets: List<ExerciseSet> )

  suspend fun deleteExerciseSet(workoutSet: ExerciseSet)

  suspend fun deleteWorkout(workout: Workout)

}

internal class WorkoutRepositoryImpl(val workoutDatastore: WorkoutDatastore)  : WorkoutRepository {
  override fun monitorWorkout(workoutId: Long): LiveData<WorkoutWithExercises>  = workoutDatastore
    .monitorWorkout(workoutId).switchMap {
      liveData<WorkoutWithExercises> { it.asWorkoutWithExercises()  }
    }

  override suspend fun insertWorkout(workout: Workout)  = workoutDatastore.insertWorkout(workout.asDBWorkout())

  override fun getWorkoutBetweenDates(dayst: Long, dayet: Long): LiveData<List<Workout>> = workoutDatastore.getFromTable(dayst,dayet)
    .switchMap{ liveData<List<Workout>> { it.map { it.asWorkOut() } } }

  override suspend fun insertExerciseSet(workoutSet: ExerciseSet) = workoutDatastore.insertExerciseSet(workoutSet.asDBExerciseSet())

  override suspend fun insertExerciseSets(workoutSets: List<ExerciseSet>) =
    workoutDatastore.insertExerciseSets(workoutSets.map { it.asDBExerciseSet() })

  override suspend fun deleteExerciseSet(workoutSet: ExerciseSet) = workoutDatastore.deleteExerciseSet(workoutSet.asDBExerciseSet())

  override suspend fun deleteWorkout(workout: Workout)  =workoutDatastore.deleteWorkout(workout.asDBWorkout())


  }