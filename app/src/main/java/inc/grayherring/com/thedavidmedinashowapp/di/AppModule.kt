package inc.grayherring.com.thedavidmedinashowapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import inc.grayherring.com.thedavidmedinashowapp.data.persistence.EntryDatabase
import javax.inject.Singleton

@Module
class AppModule {

  @Provides
  @Singleton
  fun providesEntryLogDatabase(application: Application) =
    Room.databaseBuilder(
      application,
      EntryDatabase::class.java,
      "log_db"
    ).build()

  @Provides
  @Singleton
  fun providesEntryDoa(database: EntryDatabase) =
    database.entryDao()


}