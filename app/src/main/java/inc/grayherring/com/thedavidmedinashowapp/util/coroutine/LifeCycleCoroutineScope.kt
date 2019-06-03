package inc.grayherring.com.thedavidmedinashowapp.util.coroutine

import androidx.lifecycle.Lifecycle.Event.ON_STOP
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import java.io.Closeable
import kotlin.coroutines.CoroutineContext

class LifeCycleCoroutineScope() : CoroutineScope, LifecycleObserver, Closeable {
  override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Main

  @OnLifecycleEvent(ON_STOP)
  override fun close() {
    coroutineContext.cancel()
  }


}