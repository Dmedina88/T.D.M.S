package inc.grayherring.com.thedavidmedinashowapp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.EntryFlowFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages.DatePickerFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages.NotesFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages.PhotoFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages.EntryTypeFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.calendar.CalenderFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.detail.LogDetailFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.entrylist.EntryListFragment

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
  @ContributesAndroidInjector
  abstract fun contributeCalenderFragment(): CalenderFragment

  @ContributesAndroidInjector
  abstract fun contributeEntryListFragment(): EntryListFragment

  @ContributesAndroidInjector
  abstract fun contributeDatePickerFragment(): DatePickerFragment

  @ContributesAndroidInjector
  abstract fun contributeNotesFragment(): NotesFragment

  @ContributesAndroidInjector
  abstract fun contributePhotoFragment(): PhotoFragment

  @ContributesAndroidInjector
  abstract fun contributeEntryFlowFragment(): EntryFlowFragment

  @ContributesAndroidInjector
  abstract fun contributeEntryTypeFragment(): EntryTypeFragment


  @ContributesAndroidInjector
  abstract fun contributeLogDetailFragment(): LogDetailFragment


}