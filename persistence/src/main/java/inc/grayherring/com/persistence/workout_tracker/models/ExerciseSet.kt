package inc.grayherring.com.persistence.workout_tracker.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
  tableName = "workout_set",
  foreignKeys = [ForeignKey(
    entity = Workout::class,
    parentColumns = ["id"],
    childColumns = ["workoutId"]
  )],
  indices = [Index(value = ["exerciseName","workoutId"])]
)
data class ExerciseSet(
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0,
  val workoutId: Long,
  val exerciseName: String,
  val order: Int,
  val reps: Int
)