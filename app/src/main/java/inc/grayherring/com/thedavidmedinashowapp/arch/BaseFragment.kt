package inc.grayherring.com.thedavidmedinashowapp.arch

import androidx.fragment.app.Fragment
import inc.grayherring.com.thedavidmedinashowapp.util.coroutine.LifeCycleCoroutineScope

abstract class BaseFragment : Fragment() {
  val fragmentScope by lazy {
    val scope = LifeCycleCoroutineScope()
    lifecycle.addObserver(scope)
    scope
  }
}