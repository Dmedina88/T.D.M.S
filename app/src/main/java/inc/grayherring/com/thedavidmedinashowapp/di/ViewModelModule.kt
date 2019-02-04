package inc.grayherring.com.thedavidmedinashowapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import inc.grayherring.com.thedavidmedinashowapp.ui.CalendarViewModel
import inc.grayherring.com.thedavidmedinashowapp.ui.ViewModelFactory

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CalendarViewModel::class)
    abstract fun bindUserViewModel(calendarViewModel: CalendarViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
