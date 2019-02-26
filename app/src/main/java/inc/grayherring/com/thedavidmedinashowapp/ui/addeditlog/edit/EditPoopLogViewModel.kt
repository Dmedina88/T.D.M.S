package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import inc.grayherring.com.thedavidmedinashowapp.data.PoopLog
import inc.grayherring.com.thedavidmedinashowapp.data.PoopLogRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditPoopLogViewModel @Inject constructor(private val poopLogRepository: PoopLogRepository) :
    ViewModel() {

  private val viewModelJob = Job()
  private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var id: Int = 0
    lateinit var oldPoopLog: LiveData<PoopLog>

    fun init(id: Int) {
        this.id = id
        oldPoopLog = poopLogRepository.getPoop(id)
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
