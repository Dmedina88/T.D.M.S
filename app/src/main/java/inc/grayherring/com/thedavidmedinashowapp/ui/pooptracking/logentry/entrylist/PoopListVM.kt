package inc.grayherring.com.thedavidmedinashowapp.ui.pooptracking.logentry.entrylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import inc.grayherring.com.core.models.Entry
import inc.grayherring.com.repository.NasaRepository
import inc.grayherring.com.thedavidmedinashowapp.util.DefaultDateFormatter
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import timber.log.Timber

class PoopListVM(
 val entryRepository: inc.grayherring.com.repository.EntryRepository,
  private val nasaRepository: NasaRepository
) : ViewModel() {
  val entryItems: LiveData<List<EntryListItem>> =
    entryRepository.getAllEntries().switchMap(::addDateItem)



  fun getEntryItems(epochDay: Long): LiveData<List<EntryListItem>>  = liveData(Dispatchers.IO) {
    emitSource(entryRepository
      .getEntries(epochDay,epochDay)
      .switchMap(::addDateItem))
  }


  private fun addDateItem(list: List<Entry>): LiveData<List<EntryListItem>> =
    liveData(Dispatchers.IO) {

      val results = mutableListOf<EntryListItem>()
      if (list.isEmpty()) {
        emit(emptyList<EntryListItem>())
      } else {
        var lastDate = list.first().date
        results.add(EntryListItem.Date(lastDate.format(DefaultDateFormatter)))
        list.forEach {
          if (lastDate.isBefore(it.date)) {
            lastDate = it.date
            results.add(EntryListItem.Date(lastDate.format(DefaultDateFormatter)))
          }
          results.add(EntryListItem.Log(it))
        }
        emit(results)
      }
    }

  val handler = CoroutineExceptionHandler { _, exception ->
    Timber.e(exception)
  }

  fun dateClicked(date: String) {

    viewModelScope.launch(handler) {

      val response = nasaRepository.getNasaPlanetary(LocalDate.parse(date, DefaultDateFormatter))
      Timber.i(response.toString())
    }
  }

}
