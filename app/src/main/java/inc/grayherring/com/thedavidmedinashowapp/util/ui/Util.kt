package inc.grayherring.com.thedavidmedinashowapp.util.ui

import android.widget.DatePicker
import java.util.Calendar

val DatePicker.date: Calendar
  get() = {
    val day = this.dayOfMonth
    val month = this.month + 1
    val year = this.year

    val calender = Calendar.getInstance()
    calender.set(year, month, day)
    calender
  }()