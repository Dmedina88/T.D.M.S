package inc.grayherring.com.thedavidmedinashowapp.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations


fun <T, R> LiveData<T>.map(mapper: (T) -> R) = Transformations.map(this, mapper)