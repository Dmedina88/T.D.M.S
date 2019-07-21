package inc.grayherring.com.core.models

import org.threeten.bp.LocalDate



data class Workout(
  val id: Long = 0,
  val date: LocalDate
)


data class WorkoutWithExercises(
  val workout: Workout,
  var exerciseSets: List<ExerciseSet> = emptyList()
)