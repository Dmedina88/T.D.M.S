package inc.grayherring.com.thedavidmedinashowapp.di

import inc.grayherring.com.thedavidmedinashowapp.ui.pooptracking.addeditlog.PoopFlowViewModel
import inc.grayherring.com.thedavidmedinashowapp.ui.pooptracking.detail.LogDetailViewModel
import inc.grayherring.com.thedavidmedinashowapp.ui.pooptracking.logentry.entrylist.PoopListVM
import inc.grayherring.com.thedavidmedinashowapp.ui.workout.WorkoutCalenderVM
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

  viewModel<WorkoutCalenderVM>()

  viewModel<PoopListVM>()

  viewModel<PoopFlowViewModel>()

  viewModel<LogDetailViewModel>()
}