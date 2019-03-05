package inc.grayherring.com.thedavidmedinashowapp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.PoopFlowFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.add.AddLogFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.edit.EditLogFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages.DatePickerFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages.NotesFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages.PhotoFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages.PoopTypeFragment
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

  @ContributesAndroidInjector
  abstract fun contributeDatePickerFragment(): DatePickerFragment

  @ContributesAndroidInjector
  abstract fun contributeNotesFragment(): NotesFragment

  @ContributesAndroidInjector
  abstract fun contributePhotoFragment(): PhotoFragment

  @ContributesAndroidInjector
  abstract fun contributePoopFlowFragment(): PoopFlowFragment

  @ContributesAndroidInjector
  abstract fun contributePoopTypeFragment(): PoopTypeFragment



}