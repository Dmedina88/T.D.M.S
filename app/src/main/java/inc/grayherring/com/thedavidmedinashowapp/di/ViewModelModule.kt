package inc.grayherring.com.thedavidmedinashowapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import inc.grayherring.com.thedavidmedinashowapp.ui.CalendarVM
import inc.grayherring.com.thedavidmedinashowapp.ui.ViewModelFactory

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CalendarVM::class)
    abstract fun bindUserViewModel(calendarVM: CalendarVM): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
