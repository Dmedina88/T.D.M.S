package inc.grayherring.com.thedavidmedinashowapp.util.ui

import android.widget.DatePicker
import org.threeten.bp.LocalDate


val DatePicker.date: LocalDate
  get() = {
    val day = this.dayOfMonth
    val month = this.month + 1
    val year = this.year

      LocalDate.of(year, month, day)
  }()



