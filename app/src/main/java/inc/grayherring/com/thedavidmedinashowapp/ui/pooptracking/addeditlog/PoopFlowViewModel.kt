package inc.grayherring.com.thedavidmedinashowapp.ui.pooptracking.addeditlog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inc.grayherring.com.core.models.Entry
import inc.grayherring.com.core.models.EntryType
import inc.grayherring.com.thedavidmedinashowapp.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate

data class EntryTypeItem(val poopType: EntryType, val selected: Boolean)

sealed class EntryFlowError {
  object MissingEntryType : EntryFlowError()
}

class PoopFlowViewModel(private val entryRepository: inc.grayherring.com.repository.EntryRepository) :
  ViewModel() {

  //MaybeD0: considering movingall this to a state class and usinging map on LiveData to get the data from it
  //id lose 2 way data binding but do i even want it  :shrug:
  private val _poopTypeList = MutableLiveData<List<EntryTypeItem>>()
  private val _errors = SingleLiveEvent<EntryFlowError>()
  private val _finish = SingleLiveEvent<Boolean>()
  private var selectedEntryType: EntryType? = null
  private var id: Int = 0

  val date = MutableLiveData<LocalDate>()
  val name = MutableLiveData<String>()
  val notes = MutableLiveData<String>()
  val imagePath = MutableLiveData<String>()
  val poopTypeList: LiveData<List<EntryTypeItem>> get() = _poopTypeList
  val errors: LiveData<EntryFlowError> get() = _errors
  val finish: LiveData<Boolean> get() = _finish

  //todo: test?
  fun selectEntryType(selectedEntryType: EntryType) {
    if (this.selectedEntryType == selectedEntryType) {
      return
    }
    this.selectedEntryType = selectedEntryType

    viewModelScope.launch {
      _poopTypeList.value = withContext(Dispatchers.IO) {
        _poopTypeList.value?.map {
          if (it.poopType == selectedEntryType) {
            it.copy(selected = true)
          } else {
            it.copy(selected = false)
          }
        }
      }
    }
  }

  fun init(id: Int, epochDay: Long) {
    if (id > 0) {
      viewModelScope.launch {
        setData(entryRepository.getEntry(id))
      }
    } else {
      reset()
      if (epochDay >-1){
        date.value = LocalDate.ofEpochDay(epochDay)
      }
    }

  }

  private fun setData(entry: Entry) {
    this@PoopFlowViewModel.id = entry.id
    date.value = entry.date
    name.value = entry.name
    notes.value = entry.notes
    imagePath.value = entry.imagePath
    selectedEntryType = entry.poopType
    _poopTypeList.value = EntryType.values().map { EntryTypeItem(it, it == entry.poopType) }
  }

  private fun reset() {
    this.id = 0
    date.value = LocalDate.now()
    name.value = ""
    notes.value = ""
    imagePath.value = ""
    selectedEntryType = null
    _poopTypeList.value = EntryType.values().map { EntryTypeItem(it, false) }
  }

  fun save() {
    viewModelScope.launch {
      val selectedDate = date.value ?: LocalDate.now()
      val selectedName = name.value ?: ""
      val selectedNotes = notes.value ?: ""
      val selectedPath = imagePath.value ?: ""
      val type = selectedEntryType

      if (type == null) {
        _errors.value = EntryFlowError.MissingEntryType
        return@launch
      }

      entryRepository.insert(
        Entry(
          selectedDate,
          type,
          selectedName,
          selectedPath,
          selectedNotes,
          id
        )
      )
      _finish.value = true
    }
  }

}



