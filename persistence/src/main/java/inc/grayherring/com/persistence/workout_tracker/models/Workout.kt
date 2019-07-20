package inc.grayherring.com.persistence.workout_tracker.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import org.threeten.bp.LocalDate

@Entity(tableName = "workout")
data class Workout(
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0,
  val date: LocalDate
)


@Entity(tableName = "workout")
data class WorkoutExercises(
  @Embedded val workout: Workout,
  @Relation(parentColumn = "id", entityColumn = "workoutId")
  var exerciseSets: List<ExerciseSet> = emptyList()
)