package inc.grayherring.com.thedavidmedinashowapp.ui.entrylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import inc.grayherring.com.thedavidmedinashowapp.data.EntryRepository
import inc.grayherring.com.thedavidmedinashowapp.data.models.Entry
import inc.grayherring.com.thedavidmedinashowapp.util.map
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

class EntryListVM @Inject constructor(val entryRepository: EntryRepository) : ViewModel() {

  val poopListItems: LiveData<List<EntryListItem>> =
    entryRepository.getAllEntries().map(::addDateItem)

  //todo: test and move off save thread
  private fun addDateItem(list: List<Entry>): List<EntryListItem> {
    val results = mutableListOf<EntryListItem>()
    if (list.isEmpty()) return results
    var lastDate = list.first().date
    results.add(EntryListItem.Date(lastDate.format(DateTimeFormatter.ISO_DATE)))
    list.forEach {
      if (lastDate.isBefore(it.date)) {
        lastDate = it.date
        results.add(EntryListItem.Date(lastDate.format(DateTimeFormatter.ISO_DATE)))
      }
      results.add(EntryListItem.Log(it))
    }
    return results
  }

}
