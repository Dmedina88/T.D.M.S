package inc.grayherring.com.repository.util

import inc.grayherring.com.core.models.Entry
import inc.grayherring.com.core.models.ExerciseSet
import inc.grayherring.com.core.models.Workout
import inc.grayherring.com.core.models.WorkoutWithExercises
import inc.grayherring.com.persistence.models.PoopEntry
import inc.grayherring.com.persistence.workout_tracker.models.WorkoutExercises
import inc.grayherring.com.persistence.workout_tracker.models.ExerciseSet as DBExerciseSet
import inc.grayherring.com.persistence.workout_tracker.models.Workout as DBWorkout

fun PoopEntry.toEntry() = Entry(
  date = date,
  poopType = poopType,
  name = name,
  imagePath = imagePath,
  notes = notes,
  id = id
)

fun Entry.toDBEntry() = PoopEntry(
  date = date,
  poopType = poopType,
  name = name,
  imagePath = imagePath,
  notes = notes,
  id = id
)

fun DBWorkout.asWorkOut() = Workout(this.id, this.date)

fun Workout.asDBWorkout() = DBWorkout(this.id, this.date)

fun DBExerciseSet.asExerciseSet() =
  ExerciseSet(
    id = this.id,
    workoutId = this.workoutId,
    exerciseName = this.exerciseName,
    order = this.order,
    reps = this.reps
  )

fun ExerciseSet.asDBExerciseSet() =
  DBExerciseSet(
    id = this.id,
    workoutId = this.workoutId,
    exerciseName = this.exerciseName,
    order = this.order,
    reps = this.reps
  )

fun WorkoutExercises.asWorkoutWithExercises() = WorkoutWithExercises(
  this.workout.asWorkOut(),
  this.exerciseSets.map { it.asExerciseSet() }
)