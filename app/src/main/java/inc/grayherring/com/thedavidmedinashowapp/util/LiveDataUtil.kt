package inc.grayherring.com.thedavidmedinashowapp.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

//todo change this to be able to move the observer to a fn
fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
  observe(lifecycleOwner, object : Observer<T> {
    override fun onChanged(t: T?) {
      observer.onChanged(t)
      removeObserver(this)
    }
  })
}

//class SingleLiveEvent<T> : MutableLiveData<T>() {
//
//  private val pending = AtomicBoolean(false)
//
//  @MainThread
//  override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
//    super.observe(owner, Observer<T> { t ->
//      if (pending.compareAndSet(true, false)) {
//        observer.onChanged(t)
//      }
//    })
//  }
//
//  @MainThread
//  override fun setValue(@Nullable t: T?) {
//    pending.set(true)
//    super.setValue(t)
//  }
//
//  /**
//   * Used for cases where T is Void, to make calls cleaner.
//   */
//  @MainThread
//  fun call() {
//    value = null
//  }
//
//}

/*
open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}
 */
