package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import inc.grayherring.com.thedavidmedinashowapp.data.PoopLogRepository
import inc.grayherring.com.thedavidmedinashowapp.data.models.PoopLog
import inc.grayherring.com.thedavidmedinashowapp.data.models.PoopType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import javax.inject.Inject

//move to won package if there are more flow spasific models
data class PoopTypeItem(val poopType: PoopType, val selected: Boolean)

class PoopFlowViewModel @Inject constructor(private val poopLogRepository: PoopLogRepository) :
  ViewModel() {

  //view data
  val date = MutableLiveData<LocalDate>()
  val name = MutableLiveData<String>()
  val notes = MutableLiveData<String>()
  val imagePath = MutableLiveData<String>()
  val poopTypeList: LiveData<List<PoopTypeItem>> get() = _poopTypeList
  private val _poopTypeList = MutableLiveData<List<PoopTypeItem>>()

  private val viewModelJob = Job()
  private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
  private var id: Int = 0
  private var selectedPoopType: PoopType? = null

  init {
    _poopTypeList.value = PoopType.values().map { PoopTypeItem(it,false) }
  }

  //take off main thread and clean up
  fun selectPoopType(selectedPoopType: PoopType) {
    if (this.selectedPoopType == selectedPoopType) {
      return
    } else {
      this.selectedPoopType = selectedPoopType
      _poopTypeList.value = _poopTypeList.value?.map {
        if (it.poopType == selectedPoopType) {
          it.copy(selected = true)
        } else {
          it.copy(selected = false)
        }
      }
    }
  }

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

