package inc.grayherring.com.persistence.workout_tracker

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import inc.grayherring.com.persistence.workout_tracker.models.ExerciseSet
import inc.grayherring.com.persistence.workout_tracker.models.Workout
import inc.grayherring.com.persistence.workout_tracker.models.WorkoutExercises

private const val GET_WORKOUT_QUERY = "SELECT * FROM workout WHERE id = :id LIMIT 1"

@Dao
interface WorkoutDao {
  @Transaction
  @Query(GET_WORKOUT_QUERY)
  fun monitorWorkout(id: Long): LiveData<WorkoutExercises>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertWorkout(workout: Workout)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertWorkoutExercises(workout: WorkoutExercises)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertExerciseSet(workoutSet: ExerciseSet)

  @Delete
  suspend fun deleteExerciseSet(workoutSet: ExerciseSet)

  @Delete
  suspend fun deleteWorkout(workout: Workout)

  @Query("SELECT * FROM workout WHERE date BETWEEN :dayst AND :dayet")
  fun getFromTable(dayst: Long, dayet: Long): LiveData<List<Workout>>



}