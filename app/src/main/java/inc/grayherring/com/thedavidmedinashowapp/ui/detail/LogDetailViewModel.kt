package inc.grayherring.com.thedavidmedinashowapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import inc.grayherring.com.thedavidmedinashowapp.arch.ViewModelCoroutine
import inc.grayherring.com.thedavidmedinashowapp.data.PoopLogRepository
import inc.grayherring.com.thedavidmedinashowapp.data.models.PoopLog
import inc.grayherring.com.thedavidmedinashowapp.data.models.PoopType
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.PoopFlowError
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.PoopFlowError.MissingPoopType
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.PoopTypeItem
import inc.grayherring.com.thedavidmedinashowapp.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import timber.log.Timber
import javax.inject.Inject



class LogDetailViewModel @Inject constructor(private val poopLogRepository: PoopLogRepository) :
  ViewModelCoroutine() {

  lateinit var logLiveData :  LiveData<PoopLog>


  fun init(id : Int){
    logLiveData = poopLogRepository.getPoopLiveData(id)
  }




}

