package inc.grayherring.com.thedavidmedinashowapp.ui.entrylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import inc.grayherring.com.thedavidmedinashowapp.data.models.Entry
import inc.grayherring.com.thedavidmedinashowapp.data.repo.EntryRepository
import inc.grayherring.com.thedavidmedinashowapp.data.repo.NasaRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import timber.log.Timber

class EntryListVM(
  entryRepository: EntryRepository,
  private val nasaRepository: NasaRepository
) : ViewModel() {


  val entryItems: LiveData<List<EntryListItem>> =
    entryRepository.getAllEntries().switchMap(::addDateItem)

  private val dateFormatter = DateTimeFormatter.ISO_DATE

   private fun addDateItem(list: List<Entry>): LiveData<List<EntryListItem>> = liveData(Dispatchers.IO){

    val results = mutableListOf<EntryListItem>()
    if (list.isEmpty()) {emit(emptyList())}
     else {
      var lastDate = list.first().date
      results.add(EntryListItem.Date(lastDate.format(dateFormatter)))
      list.forEach {
        if (lastDate.isBefore(it.date)) {
          lastDate = it.date
          results.add(EntryListItem.Date(lastDate.format(dateFormatter)))
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

      val response = nasaRepository.getNasaPlanetary(LocalDate.parse(date, dateFormatter))
      Timber.i(response.toString())
    }
  }

//  fun onDateClicked
}
