package inc.grayherring.com.thedavidmedinashowapp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.add.AddLogFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.edit.EditLogFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.calendar.CalenderFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.pooplist.PoopListFragment

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
  @ContributesAndroidInjector
  abstract fun contributeCalenderFragment(): CalenderFragment

  @ContributesAndroidInjector
  abstract fun contributePoopListFragment(): PoopListFragment

  @ContributesAndroidInjector
  abstract fun contributeAddLogFragment(): AddLogFragment

  @ContributesAndroidInjector
  abstract fun contributeEditLogFragment(): EditLogFragment

}