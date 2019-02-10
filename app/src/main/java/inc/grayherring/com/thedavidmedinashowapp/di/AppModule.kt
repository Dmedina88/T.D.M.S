package inc.grayherring.com.thedavidmedinashowapp.di

import android.app.Application
import androidx.room.Room
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import inc.grayherring.com.thedavidmedinashowapp.data.PoopLogDatabase
import javax.inject.Singleton
import inc.grayherring.com.thedavidmedinashowapp.ui.MainActivity
import dagger.android.ContributesAndroidInjector
import inc.grayherring.com.thedavidmedinashowapp.TheDavidMedinaShowApp

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