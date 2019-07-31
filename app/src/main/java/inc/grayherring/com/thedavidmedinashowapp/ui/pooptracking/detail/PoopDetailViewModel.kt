package inc.grayherring.com.thedavidmedinashowapp.ui.pooptracking.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inc.grayherring.com.core.models.Entry
import inc.grayherring.com.thedavidmedinashowapp.ui.pooptracking.detail.AnimationState.FULL_DETAIL
import inc.grayherring.com.thedavidmedinashowapp.ui.pooptracking.detail.AnimationState.IMAGE_FULLSCREEN
import inc.grayherring.com.thedavidmedinashowapp.ui.pooptracking.detail.AnimationState.NONE
import inc.grayherring.com.thedavidmedinashowapp.util.SingleLiveEvent
import kotlinx.coroutines.launch

data class LogDetailState(val entry: Entry, val animationState: AnimationState)

enum class AnimationState {
  NONE,
  IMAGE_FULLSCREEN,
  FULL_DETAIL
}

class LogDetailViewModel(private val entryRepository: inc.grayherring.com.repository.EntryRepository) :
  ViewModel() {

  private val _deletedLiveData = SingleLiveEvent<Boolean>()
  private val _logDetailState = MutableLiveData<LogDetailState>()

  val logDetailState get() = _logDetailState
  val deletedLiveData get() = _deletedLiveData

  fun init(id: Int) {
    viewModelScope.launch {
      val log = entryRepository.getEntry(id)
      _logDetailState.value = LogDetailState(log, NONE)
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
    viewModelScope.launch {
      _logDetailState.value?.let {
        entryRepository.deleteEntry(it.entry)
        deletedLiveData.value = true
      }
    }
  }

}

