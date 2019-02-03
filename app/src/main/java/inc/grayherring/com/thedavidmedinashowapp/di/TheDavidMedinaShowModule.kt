import dagger.Module
import dagger.android.ContributesAndroidInjector
import inc.grayherring.com.thedavidmedinashowapp.ui.MainActivity

@Module
abstract class TheDavidMedinaShowModule {
    @ContributesAndroidInjector
     abstract fun contributeActivityInjector(): MainActivity
}