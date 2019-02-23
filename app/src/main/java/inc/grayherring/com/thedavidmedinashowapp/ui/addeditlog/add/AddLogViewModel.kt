package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.add

import androidx.lifecycle.ViewModel
import inc.grayherring.com.thedavidmedinashowapp.data.PoopLog
import inc.grayherring.com.thedavidmedinashowapp.data.PoopLogRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddLogViewModel @Inject constructor(private val poopLogRepository: PoopLogRepository) :
    ViewModel() {

  private val viewModelJob = Job()
  private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

  fun save(poop: PoopLog) {
    uiScope.launch(Dispatchers.IO) {
      poopLogRepository.insert(poop)
    }
  }

  override fun onCleared() {
    super.onCleared()
    viewModelJob.cancel()
  }
}
