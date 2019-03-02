package inc.grayherring.com.thedavidmedinashowapp.util

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations


fun <X, Y> LiveData<X>.map(mapper: (X) -> Y) = Transformations.map(this, mapper)

fun <X> LiveData<X>.distinctUntilChanged() = Transformations.distinctUntilChanged(this)

