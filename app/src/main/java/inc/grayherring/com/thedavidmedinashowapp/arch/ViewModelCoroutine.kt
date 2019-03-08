package inc.grayherring.com.thedavidmedinashowapp.arch

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


//i thought i read some place that View<odel ktx should have viewModeScope packaged in it
//delete if ever finds it
open class ViewModelCoroutine : ViewModel(){
  private val viewModelJob = Job()
  protected val viewModeScope = CoroutineScope(Dispatchers.Main + viewModelJob)

  override fun onCleared() {
    super.onCleared()
    viewModelJob.cancel()
  }
}