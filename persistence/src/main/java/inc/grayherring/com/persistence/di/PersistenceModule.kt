package inc.grayherring.com.persistence.di

import androidx.room.Room
import inc.grayherring.com.persistence.EntryDatastore
import inc.grayherring.com.persistence.EntryDatastoreImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.experimental.builder.singleBy

val persistenceModule = module {

  single {
    Room.databaseBuilder(
      androidContext(),
      inc.grayherring.com.persistence.EntryDatabase::class.java,
      "log_db"
    ).build().entryDao()
  }

  singleBy<EntryDatastore, EntryDatastoreImpl>()


}