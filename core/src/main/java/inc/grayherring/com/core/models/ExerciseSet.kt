package inc.grayherring.com.core.models

data class ExerciseSet(
  val id: Long = 0,
  val workoutId: Long,
  val exerciseName: String,
  val order: Int,
  val reps: Int
)