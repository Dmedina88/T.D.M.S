package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import inc.grayherring.com.thedavidmedinashowapp.arch.ViewModelCoroutine
import inc.grayherring.com.thedavidmedinashowapp.data.EntryRepository
import inc.grayherring.com.thedavidmedinashowapp.data.models.Entry
import inc.grayherring.com.thedavidmedinashowapp.data.models.EntryType
import inc.grayherring.com.thedavidmedinashowapp.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import javax.inject.Inject

data class EntryTypeItem(val poopType: EntryType, val selected: Boolean)

sealed class EntryFlowError {
  object MissingEntryType : EntryFlowError()
}

class EntryFlowViewModel @Inject constructor(private val entryRepository: EntryRepository) :
  ViewModelCoroutine() {

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

    viewModeScope.launch {
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

  fun init(id: Int) {
    if (id > 0) {
      viewModeScope.launch {
        setData(entryRepository.getEntry(id))
      }
    } else {
      reset()
    }

  }

  private fun setData(entry: Entry) {
    this@EntryFlowViewModel.id = entry.id
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
    viewModeScope.launch {
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



