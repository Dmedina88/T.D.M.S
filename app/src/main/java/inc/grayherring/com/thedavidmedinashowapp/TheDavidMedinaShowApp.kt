package inc.grayherring.com.thedavidmedinashowapp

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import inc.grayherring.com.thedavidmedinashowapp.di.appModule
import inc.grayherring.com.thedavidmedinashowapp.di.dataModule
import inc.grayherring.com.thedavidmedinashowapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class TheDavidMedinaShowApp : Application() {

  override fun onCreate() {
    super.onCreate()

    AndroidThreeTen.init(this)

    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }

    startKoin {
      androidLogger()
      androidContext(this@TheDavidMedinaShowApp)
      modules(appModule, dataModule, viewModelModule)
    }

  }

}