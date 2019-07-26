package inc.grayherring.com.thedavidmedinashowapp.ui.logentry.entrycalender

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import inc.grayherring.com.repository.EntryRepository
import inc.grayherring.com.thedavidmedinashowapp.ui.calendar.CalenderIteam
import inc.grayherring.com.thedavidmedinashowapp.ui.calendar.Event
import inc.grayherring.com.thedavidmedinashowapp.ui.calendar.calenderPopulated
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import timber.log.Timber

class EntryCalenderVM(
  entryRepository: EntryRepository
) : ViewModel() {

  private val dateFormatter = DateTimeFormatter.ISO_DATE

  val calenderIteamLiveData: LiveData<List<CalenderIteam>> = entryRepository.getAllEntries()
    .switchMap {
      liveData(viewModelScope.coroutineContext) {
        Timber.d(Thread.currentThread().name)
        val events = it.groupBy { it.date }
          .mapValues { Event(it.value.first().date, "\uD83D\uDCA9 x${it.value.size}") }
          .values.toList()

        emit(
          calenderPopulated(
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
