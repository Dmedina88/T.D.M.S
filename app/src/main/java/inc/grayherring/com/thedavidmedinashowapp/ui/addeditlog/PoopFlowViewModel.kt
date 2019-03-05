package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import inc.grayherring.com.thedavidmedinashowapp.data.PoopLog
import inc.grayherring.com.thedavidmedinashowapp.data.PoopLogRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import javax.inject.Inject

class PoopFlowViewModel @Inject constructor(private val poopLogRepository: PoopLogRepository) :
  ViewModel() {

  val date = MutableLiveData<LocalDate>()
  val name = MutableLiveData<String>()
  val notes = MutableLiveData<String>()
  val imagePath = MutableLiveData<String>()

  // val selected = MutableLiveData<PoopTypeItem>()

  private val viewModelJob = Job()
  private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
  private var id: Int = 0

  fun init(id: Int?) {
    this.id = id ?: 0
    date.value = LocalDate.now()

  }

  fun save(poop: PoopLog) {
    uiScope.launch(Dispatchers.IO) {
      poopLogRepository.insert(poop.copy(id = id))
    }
  }

  override fun onCleared() {
    super.onCleared()
    viewModelJob.cancel()
  }
}
