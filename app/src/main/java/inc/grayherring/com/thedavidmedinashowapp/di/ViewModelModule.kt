package inc.grayherring.com.thedavidmedinashowapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import inc.grayherring.com.thedavidmedinashowapp.ui.CalendarViewModel
import inc.grayherring.com.thedavidmedinashowapp.ui.ViewModelFactory
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.add.AddLogViewModel
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.edit.EditPoopLogViewModel
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
    @ViewModelKey(AddLogViewModel::class)
    abstract fun bindAddLogViewModel(addLogViewModel: AddLogViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(EditPoopLogViewModel::class)
  abstract fun bindEditLogViewModel(editPoopLogViewModel: EditPoopLogViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
