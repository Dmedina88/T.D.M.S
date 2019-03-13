package inc.grayherring.com.thedavidmedinashowapp.ui.detail

import androidx.lifecycle.LiveData
import inc.grayherring.com.thedavidmedinashowapp.arch.ViewModelCoroutine
import inc.grayherring.com.thedavidmedinashowapp.data.PoopLogRepository
import inc.grayherring.com.thedavidmedinashowapp.data.models.PoopLog
import javax.inject.Inject

class LogDetailViewModel @Inject constructor(private val poopLogRepository: PoopLogRepository) :
  ViewModelCoroutine() {

  private lateinit var _logLiveData : LiveData<PoopLog>
  val logLiveData get() = _logLiveData

  fun init(id: Int){
    _logLiveData = poopLogRepository.getPoopLiveData(id)
  }

}

