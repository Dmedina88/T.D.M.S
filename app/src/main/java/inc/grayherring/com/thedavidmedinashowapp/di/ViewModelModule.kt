package inc.grayherring.com.thedavidmedinashowapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import inc.grayherring.com.thedavidmedinashowapp.ui.ViewModelFactory
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.PoopFlowViewModel
import inc.grayherring.com.thedavidmedinashowapp.ui.calendar.CalendarViewModel
import inc.grayherring.com.thedavidmedinashowapp.ui.pooplist.PoopListVM

@Suppress("unused")
@Module
abstract class ViewModelModule {
  @Binds
  @IntoMap
  @ViewModelKey(CalendarViewModel::class)
  abstract fun bindCalenderViewModel(calendarViewModel: CalendarViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(PoopListVM::class)
  abstract fun bindListViewModel(poopListVM: PoopListVM): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(PoopFlowViewModel::class)
  abstract fun bindPoopFlowViewModel(poopFlowViewModel: PoopFlowViewModel): ViewModel

  @Binds
  abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
