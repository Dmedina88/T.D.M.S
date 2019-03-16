package inc.grayherring.com.thedavidmedinashowapp.ui.detail

import androidx.lifecycle.MutableLiveData
import inc.grayherring.com.thedavidmedinashowapp.arch.ViewModelCoroutine
import inc.grayherring.com.thedavidmedinashowapp.data.PoopLogRepository
import inc.grayherring.com.thedavidmedinashowapp.data.models.PoopLog
import inc.grayherring.com.thedavidmedinashowapp.ui.detail.AnimationState.FULL_DETAIL
import inc.grayherring.com.thedavidmedinashowapp.ui.detail.AnimationState.IMAGE_FULLSCREEN
import inc.grayherring.com.thedavidmedinashowapp.ui.detail.AnimationState.NONE
import inc.grayherring.com.thedavidmedinashowapp.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class LogDetailState(val log: PoopLog, val animationState: AnimationState)

enum class AnimationState {
  NONE,
  IMAGE_FULLSCREEN,
  FULL_DETAIL
}

class LogDetailViewModel @Inject constructor(private val poopLogRepository: PoopLogRepository) :
  ViewModelCoroutine() {

  private val _deletedLiveData = SingleLiveEvent<Boolean>()
  private val _logDetailState = MutableLiveData<LogDetailState>()

  val logDetailState get() = _logDetailState
  val deletedLiveData get() = _deletedLiveData

  fun init(id: Int) {
    viewModeScope.launch {
      _logDetailState.value = withContext(Dispatchers.IO) {
        val log = poopLogRepository.getPoop(id)
        LogDetailState(log, NONE)
      }
    }
  }

  fun toggleImage() {
    _logDetailState.apply {
      value = value?.copy(
        animationState =
        if (value?.animationState == NONE || value?.animationState == FULL_DETAIL) IMAGE_FULLSCREEN else FULL_DETAIL
      )
    }
  }

  fun delete() {
    viewModeScope.launch {
      deletedLiveData.value = withContext(Dispatchers.IO) {
        _logDetailState.value?.let {
          poopLogRepository.deletePoopLog(it.log)
          true
        }
      }
    }
  }
}

