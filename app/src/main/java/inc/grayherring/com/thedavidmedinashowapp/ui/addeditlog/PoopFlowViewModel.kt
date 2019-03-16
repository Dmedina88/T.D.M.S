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
import timber.log.Timber
import javax.inject.Inject

data class PoopTypeItem(val poopType: PoopType, val selected: Boolean)

sealed class PoopFlowError {
  object MissingPoopType : PoopFlowError()
}

class PoopFlowViewModel @Inject constructor(private val poopLogRepository: PoopLogRepository) :
  ViewModelCoroutine() {

  //MaybeD0: considering movingall this to a state class and usinging map on LiveData to get the data from it
  //id lose 2 way data binding but do i even want it  :shrug:
  private val _poopTypeList = MutableLiveData<List<PoopTypeItem>>()
  private val _errors = SingleLiveEvent<PoopFlowError>()
  private val _finish = SingleLiveEvent<Boolean>()
  private var selectedPoopType: PoopType? = null
  private var id: Int = 0

  val date = MutableLiveData<LocalDate>()
  val name = MutableLiveData<String>()
  val notes = MutableLiveData<String>()
  val imagePath = MutableLiveData<String>()
  val poopTypeList: LiveData<List<PoopTypeItem>> get() = _poopTypeList
  val errors: LiveData<PoopFlowError> get() = _errors
  val finish: LiveData<Boolean> get() = _finish

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

  fun init(id: Int) {
    if (id > 0) {
      viewModeScope.launch {
        val log = withContext(Dispatchers.IO) {
          poopLogRepository.getPoop(id)
        }
        setData(log)
      }
    } else {
      reset()
    }

  }

  private fun setData(log: PoopLog) {
    this@PoopFlowViewModel.id = log.id
    date.value = log.date
    name.value = log.name
    notes.value = log.notes
    imagePath.value = log.imagePath
    selectedPoopType = log.poopType
    _poopTypeList.value = PoopType.values().map { PoopTypeItem(it, it == log.poopType) }
  }

  private fun reset() {
    this.id = 0
    date.value = LocalDate.now()
    name.value = ""
    notes.value = ""
    imagePath.value = ""
    selectedPoopType = null
    _poopTypeList.value = PoopType.values().map { PoopTypeItem(it, false) }
  }

  fun save() {
    viewModeScope.launch {
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

