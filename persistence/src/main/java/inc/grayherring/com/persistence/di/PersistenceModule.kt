package inc.grayherring.com.persistence.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val persistenceModule = module {

  single {
    Room.databaseBuilder(
      androidContext(),
      inc.grayherring.com.persistence.EntryDatabase::class.java,
      "log_db"
    ).build().entryDao()
  }


}