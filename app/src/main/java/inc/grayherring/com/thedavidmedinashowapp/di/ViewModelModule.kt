package inc.grayherring.com.thedavidmedinashowapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import inc.grayherring.com.thedavidmedinashowapp.ui.ViewModelFactory
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.EntryFlowViewModel
import inc.grayherring.com.thedavidmedinashowapp.ui.calendar.CalendarViewModel
import inc.grayherring.com.thedavidmedinashowapp.ui.detail.LogDetailViewModel
import inc.grayherring.com.thedavidmedinashowapp.ui.entrylist.EntryListVM

@Suppress("unused")
@Module
abstract class ViewModelModule {
  @Binds
  @IntoMap
  @ViewModelKey(CalendarViewModel::class)
  abstract fun bindCalenderViewModel(calendarViewModel: CalendarViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(EntryListVM::class)
  abstract fun bindListViewModel(entryListVM: EntryListVM): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(EntryFlowViewModel::class)
  abstract fun bindEntryFlowViewModel(poopFlowViewModel: EntryFlowViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(LogDetailViewModel::class)
  abstract fun bindLogDetailViewModel(logDetailViewModel: LogDetailViewModel): ViewModel

  @Binds
  abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
