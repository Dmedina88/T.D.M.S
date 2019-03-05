package inc.grayherring.com.thedavidmedinashowapp.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

fun <X, Y> LiveData<X>.map(mapper: (X) -> Y) = Transformations.map(this, mapper)

fun <X> LiveData<X>.distinctUntilChanged() = Transformations.distinctUntilChanged(this)

