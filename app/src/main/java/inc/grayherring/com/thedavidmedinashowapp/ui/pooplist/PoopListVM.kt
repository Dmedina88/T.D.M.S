package inc.grayherring.com.thedavidmedinashowapp.ui.pooplist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import inc.grayherring.com.thedavidmedinashowapp.data.PoopLogRepository
import inc.grayherring.com.thedavidmedinashowapp.data.models.PoopLog
import inc.grayherring.com.thedavidmedinashowapp.util.map
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

class PoopListVM @Inject constructor(val poopLogRepository: PoopLogRepository) : ViewModel() {

  val poopListItems: LiveData<List<PoopListItem>> =
    poopLogRepository.getAllPoops().map(::addDateItem)

  //todo: test and move off save thread
  private fun addDateItem(list: List<PoopLog>): List<PoopListItem> {
    val results = mutableListOf<PoopListItem>()
    if (list.isEmpty()) return results
    var lastDate = list.first().date
    results.add(PoopListItem.Date(lastDate.format(DateTimeFormatter.ISO_DATE)))
    list.forEach {
      if (lastDate.isBefore(it.date)) {
        lastDate = it.date
        results.add(PoopListItem.Date(lastDate.format(DateTimeFormatter.ISO_DATE)))
      }
      results.add(PoopListItem.Log(it))
    }
    return results
  }

}
