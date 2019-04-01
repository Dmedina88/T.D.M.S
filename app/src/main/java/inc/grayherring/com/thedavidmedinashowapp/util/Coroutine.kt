package inc.grayherring.com.thedavidmedinashowapp.util

sealed class Result {
  data class Success<T>(val result: T) : Result()
  data class Error(val error: Throwable) : Result()
}

