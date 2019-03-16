package inc.grayherring.com.thedavidmedinashowapp.util.ui

import android.view.View
import android.widget.TextView

fun TextView.textOrGone(string: String?) {
  if (string.isNullOrBlank()) {
    visibility = View.INVISIBLE
  } else {
    visibility = View.VISIBLE
    if (text != string) {
      text = string
    }

  }
}