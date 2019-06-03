package inc.grayherring.com.thedavidmedinashowapp.di

import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.EntryFlowViewModel
import inc.grayherring.com.thedavidmedinashowapp.ui.calendar.CalendarViewModel
import inc.grayherring.com.thedavidmedinashowapp.ui.detail.LogDetailViewModel
import inc.grayherring.com.thedavidmedinashowapp.ui.entrylist.EntryListVM
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

  viewModel<CalendarViewModel>()

  viewModel<EntryListVM>()

  viewModel<EntryFlowViewModel>()

  viewModel<LogDetailViewModel>()
}