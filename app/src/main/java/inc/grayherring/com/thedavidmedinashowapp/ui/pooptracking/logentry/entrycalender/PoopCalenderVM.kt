package inc.grayherring.com.thedavidmedinashowapp.ui.pooptracking.logentry.entrycalender

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import inc.grayherring.com.repository.EntryRepository
import inc.grayherring.com.thedavidmedinashowapp.ui.calendar.CalenderIteam
import inc.grayherring.com.thedavidmedinashowapp.ui.calendar.Event
import inc.grayherring.com.thedavidmedinashowapp.ui.calendar.calenderPopulater
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import timber.log.Timber

class PoopCalenderVM(
  entryRepository: EntryRepository
) : ViewModel() {


  val calenderItemLiveData: LiveData<List<CalenderIteam>> = entryRepository.getAllEntries()
    .switchMap {
      liveData(viewModelScope.coroutineContext) {
        Timber.d(Thread.currentThread().name)
        val events = it.groupBy { it.date }
          .mapValues { Event(it.value.first().date, "\uD83D\uDCA9 x${it.value.size}") }
          .values.toList()

        emit(
          calenderPopulater(
            LocalDate.now().minusMonths(6),
            LocalDate.now().plusMonths(6),
            events
          )
        )
      }
    }

//  private fun getPoopLogfromDates(list: List<Entry>){
//    entryRepository.getAllEntries().map { it.groupBy { it.date }.mapValues {  Event(it.value.first().date,   "\uD83D\uDCA9 x${it.value.size}") } }
//  }

  val handler = CoroutineExceptionHandler { _, exception ->
    Timber.e(exception)
  }

  fun dateClicked(localDate: LocalDate) {
    viewModelScope.launch(handler) {

    }
  }

}
