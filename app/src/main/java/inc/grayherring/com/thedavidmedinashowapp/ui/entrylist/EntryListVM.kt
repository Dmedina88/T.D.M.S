package inc.grayherring.com.thedavidmedinashowapp.ui.entrylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inc.grayherring.com.thedavidmedinashowapp.data.models.Entry
import inc.grayherring.com.thedavidmedinashowapp.data.repo.EntryRepository
import inc.grayherring.com.thedavidmedinashowapp.data.repo.NasaRepository
import inc.grayherring.com.thedavidmedinashowapp.util.map
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import timber.log.Timber

class EntryListVM(
  entryRepository: EntryRepository,
  private val nasaRepository: NasaRepository
) : ViewModel() {


  val entryItems: LiveData<List<EntryListItem>> =
    entryRepository.getAllEntries().map(::addDateItem)

  private val dateFormatter = DateTimeFormatter.ISO_DATE

  //todo: test and move off save thread
  private fun addDateItem(list: List<Entry>): List<EntryListItem> {

    val results = mutableListOf<EntryListItem>()
    if (list.isEmpty()) return results
    var lastDate = list.first().date
    results.add(EntryListItem.Date(lastDate.format(dateFormatter)))
    list.forEach {
      if (lastDate.isBefore(it.date)) {
        lastDate = it.date
        results.add(EntryListItem.Date(lastDate.format(dateFormatter)))
      }
      results.add(EntryListItem.Log(it))
    }
    return results
  }

  val handler = CoroutineExceptionHandler { _, exception ->
    Timber.e(exception)
  }

   fun dateClicked(date: String) {


    val  flow = flowOf(1,2,3,3)
    viewModelScope.launch(handler) {
      flow.map { it +1 }.collect {
        Timber.d(it.toString())
      }
      val response = nasaRepository.getNasaPlanetary(LocalDate.parse(date, dateFormatter))
      Timber.i(response.toString())
    }
  }

//  fun onDateClicked
}
