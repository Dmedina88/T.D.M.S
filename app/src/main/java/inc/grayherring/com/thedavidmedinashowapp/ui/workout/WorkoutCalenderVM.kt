package inc.grayherring.com.thedavidmedinashowapp.ui.workout

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

class WorkoutCalenderVM(
  val entryRepository: EntryRepository
) : ViewModel() {

  private val _calenderItemLiveData: LiveData<List<CalenderIteam>> = MutableLiveData()
  val calenderItemLiveData: LiveData<List<CalenderIteam>> = _calenderItemLiveData

  val handler = CoroutineExceptionHandler { _, exception ->
    Timber.e(exception)
  }

  fun dateClicked(localDate: LocalDate) {
    viewModelScope.launch(handler) {
    }
  }

  var count = 1L
  fun loadMore() {
    Log.d("TEStz",count.toString())
    count = count++
    _calenderItemLiveData.switchMap {
      entryRepository.getAllEntries()
        .switchMap {
          liveData(viewModelScope.coroutineContext) {
            Timber.d(Thread.currentThread().name)
            val events = it.groupBy { it.date }
              .mapValues { Event(it.value.first().date, "\uD83D\uDCA9 x${it.value.size}") }
              .values.toList()
            emit(
              calenderPopulater(
                LocalDate.now().minusMonths(6 * count),
                LocalDate.now().plusMonths(6 * count),
                events
              )
            )
          }
        }
    }
  }
}


