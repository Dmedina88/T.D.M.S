package inc.grayherring.com.thedavidmedinashowapp.util

import androidx.annotation.MainThread
import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.databinding.adapters.NumberPickerBindingAdapter.setValue
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

fun <X, Y> LiveData<X>.map(mapper: (X) -> Y) = Transformations.map(this, mapper)

fun <X> LiveData<X>.distinctUntilChanged() = Transformations.distinctUntilChanged(this)

class SingleLiveEvent<T> : MutableLiveData<T>() {

  private val pending = AtomicBoolean(false)

  @MainThread
  override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
    super.observe(owner, Observer<T> { t ->
      if (pending.compareAndSet(true, false)) {
        observer.onChanged(t)
      }
    })
  }
  @MainThread
  override fun setValue(@Nullable t: T?) {
    pending.set(true)
    super.setValue(t)
  }

  /**
   * Used for cases where T is Void, to make calls cleaner.
   */
  @MainThread
  fun call() {
    value = null
  }

}


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
