package inc.grayherring.com.thedavidmedinashowapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import inc.grayherring.com.thedavidmedinashowapp.data.PoopLogDatabase
import javax.inject.Singleton

@Module
class AppModule {

  @Provides
  @Singleton
  fun providesPoopLogDatabase(application: Application) =
    Room.databaseBuilder(
      application,
      PoopLogDatabase::class.java,
      "Poop_log_db"
    ).build()

  @Provides
  @Singleton
  fun providesPoopDoa(database: PoopLogDatabase) =
    database.poopDao()

}