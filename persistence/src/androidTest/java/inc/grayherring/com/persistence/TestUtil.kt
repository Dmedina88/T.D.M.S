package inc.grayherring.com.persistence

import inc.grayherring.com.persistence.workout_tracker.models.ExerciseSet
import inc.grayherring.com.persistence.workout_tracker.models.Workout
import inc.grayherring.com.persistence.workout_tracker.models.WorkoutExercises
import org.threeten.bp.LocalDate

fun createTestWorkout(id: Long = 0, date: LocalDate = LocalDate.MIN) = Workout(id, date)

fun createTestExerciseSet(
  id: Long = 0,
  workoutId: Long = 0,
  exerciseName: String = "chest",
  order: Int = 0,
  reps: Int = 0
) = ExerciseSet(id, workoutId, exerciseName, order, reps)

fun createWorkoutWithExercises(count: Long): List<WorkoutExercises> {
  val exerciseNames = listOf("chest", "arm", "butt", "legs")

  val exercise = exerciseNames.mapIndexed { index, name ->
    createTestExerciseSet(id = index.toLong() + 1, exerciseName = name)
  }
  val workoutExercises = mutableListOf<WorkoutExercises>()
  for (index in 0..count) {
    val workoutId = index + 1
    val workout = createTestWorkout(id = workoutId)
    val workoutExercise = WorkoutExercises(
      workout,
      exercise.map { it.copy(id = it.id * workoutId, workoutId = workoutId) })
    workoutExercises.add(workoutExercise)
  }
  return workoutExercises

}