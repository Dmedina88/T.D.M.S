package inc.grayherring.com.thedavidmedinashowapp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import inc.grayherring.com.thedavidmedinashowapp.ui.CalenderFragment

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeCalenderFragment(): CalenderFragment

}