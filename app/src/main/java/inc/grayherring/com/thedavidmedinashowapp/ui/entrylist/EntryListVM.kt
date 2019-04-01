package inc.grayherring.com.thedavidmedinashowapp.ui.entrylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inc.grayherring.com.thedavidmedinashowapp.data.models.Entry
import inc.grayherring.com.thedavidmedinashowapp.data.repo.EntryRepository
import inc.grayherring.com.thedavidmedinashowapp.data.repo.NasaRepository
import inc.grayherring.com.thedavidmedinashowapp.util.map
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import timber.log.Timber
import javax.inject.Inject

class EntryListVM @Inject constructor(
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

  fun dateClicked(date: String) {
    viewModelScope.launch {
      try {

        //  val response =  nasaRepository.getNasaPlanetary(LocalDate.parse(date, dateFormatter))
        val response = async {
          nasaRepository.getNasaPlanetary(LocalDate.parse(date, dateFormatter))
        }

        Timber.d(response.toString())

      } catch (e: Error) {
        Timber.e(e)

      }
    }
  }

//  fun onDateClicked
}
