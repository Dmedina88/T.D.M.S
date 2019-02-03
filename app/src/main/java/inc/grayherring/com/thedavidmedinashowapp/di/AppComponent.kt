package inc.grayherring.com.thedavidmedinashowapp.di;

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

import inc.grayherring.com.thedavidmedinashowapp.TheDavidMedinaShowApp

import javax.inject.Singleton;


@Singleton
@Component(modules = [ AndroidInjectionModule::class, AppModule::class,DataModule::class, MainActivityModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(davidMedinaShowApp: TheDavidMedinaShowApp)

}
