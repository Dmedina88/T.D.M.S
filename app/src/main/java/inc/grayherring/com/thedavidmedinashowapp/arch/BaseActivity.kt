package inc.grayherring.com.thedavidmedinashowapp.arch

import androidx.appcompat.app.AppCompatActivity
import inc.grayherring.com.thedavidmedinashowapp.util.coroutine.LifeCycleCoroutineScope

abstract class BaseActivity : AppCompatActivity() {
  val activityScope by lazy {
    val scope = LifeCycleCoroutineScope()
    lifecycle.addObserver(scope)
    scope
  }
}