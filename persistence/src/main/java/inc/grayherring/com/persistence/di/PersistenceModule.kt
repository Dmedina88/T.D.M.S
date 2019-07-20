package inc.grayherring.com.persistence.di

import androidx.room.Room
import inc.grayherring.com.persistence.workout_tracker.WorkoutDatabase
import inc.grayherring.com.persistence.poop_tracker.EntryDatastore
import inc.grayherring.com.persistence.poop_tracker.EntryDatastoreImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.experimental.builder.singleBy

val persistenceModule = module {

  single {
    Room.databaseBuilder(
      androidContext(),
      WorkoutDatabase::class.java,
      "log_db"
    ).build().workoutDao()
  }

  singleBy<EntryDatastore, EntryDatastoreImpl>()


}