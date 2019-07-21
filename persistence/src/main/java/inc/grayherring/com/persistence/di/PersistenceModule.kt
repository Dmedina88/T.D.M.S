package inc.grayherring.com.persistence.di

import inc.grayherring.com.persistence.poop_tracker.EntryDatabase
import inc.grayherring.com.persistence.workout_tracker.WorkoutDatabase
import inc.grayherring.com.persistence.poop_tracker.EntryDatastore
import inc.grayherring.com.persistence.poop_tracker.EntryDatastoreImpl
import inc.grayherring.com.persistence.workout_tracker.WorkoutDatastore
import inc.grayherring.com.persistence.workout_tracker.WorkoutDatastoreImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.experimental.builder.singleBy

val persistenceModule = module {

  single {
   EntryDatabase.getDatabase(androidContext()).entryDao()
  }

  singleBy<EntryDatastore, EntryDatastoreImpl>()

  single {
    WorkoutDatabase.getDatabase(androidContext()).workoutDao()
  }

  singleBy<WorkoutDatastore, WorkoutDatastoreImpl>()

}