package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import inc.grayherring.com.thedavidmedinashowapp.arch.ViewModelCoroutine
import inc.grayherring.com.thedavidmedinashowapp.data.PoopLogRepository
import inc.grayherring.com.thedavidmedinashowapp.data.models.PoopLog
import inc.grayherring.com.thedavidmedinashowapp.data.models.PoopType
import inc.grayherring.com.thedavidmedinashowapp.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import javax.inject.Inject

data class PoopTypeItem(val poopType: PoopType, val selected: Boolean)

sealed class PoopFlowError {
  object MissingPoopType : PoopFlowError()
}

class PoopFlowViewModel @Inject constructor(private val poopLogRepository: PoopLogRepository) :
  ViewModelCoroutine() {

  private val _poopTypeList = MutableLiveData<List<PoopTypeItem>>()
  private val _errors = SingleLiveEvent<PoopFlowError>()
  private val _finish = SingleLiveEvent<Boolean>()
  private var selectedPoopType: PoopType? = null
  private var id: Int = 0

  val date = MutableLiveData<LocalDate>().apply { value = LocalDate.now() }
  val name = MutableLiveData<String>().apply { value = "" }
  val notes = MutableLiveData<String>().apply { value = "" }
  val imagePath = MutableLiveData<String>().apply { value = "" }
  val poopTypeList: LiveData<List<PoopTypeItem>> get() = _poopTypeList
  val errors: LiveData<PoopFlowError> get() = _errors
  val finish: LiveData<Boolean> get() = _finish

  init {
    _poopTypeList.value = PoopType.values().map { PoopTypeItem(it, false) }
  }

  //todo: test?
  fun selectPoopType(selectedPoopType: PoopType) {
    if (this.selectedPoopType == selectedPoopType) {
      return
    }
    this.selectedPoopType = selectedPoopType

    viewModeScope.launch {
      _poopTypeList.value = withContext(Dispatchers.IO) {
        _poopTypeList.value?.map {
          if (it.poopType == selectedPoopType) {
            it.copy(selected = true)
          } else {
            it.copy(selected = false)
          }
        }
      }
    }
  }

  fun init(id: Int?) {
    this.id = id ?: 0
  }

  fun save() {
    viewModeScope.launch {
      //todo selectedPoopType error
      val selectedDate = date.value ?: LocalDate.now()
      val selectedName = name.value ?: ""
      val selectedNotes = notes.value ?: ""
      val selectedPath = imagePath.value ?: ""
      val type = selectedPoopType

      if (type == null) {
        _errors.value = PoopFlowError.MissingPoopType
        return@launch
      }

      _finish.value = withContext(Dispatchers.IO) {
        poopLogRepository.insert(
          PoopLog(selectedDate, type, selectedName, selectedPath, selectedNotes, id)
        )
        true
      }

    }

  }

}

