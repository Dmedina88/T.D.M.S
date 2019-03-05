package inc.grayherring.com.thedavidmedinashowapp.util.ui

import android.widget.DatePicker
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import org.threeten.bp.LocalDate

//todo get to work with data binding and tesst
@set:BindingAdapter("local_date")
@get:InverseBindingAdapter(attribute = "local_date")
var DatePicker.localDate: LocalDate
  get() = LocalDate.of(this.year, this.month + 1, this.dayOfMonth)
  set(value) {
    if (this.localDate != value) {
      this.updateDate(this.year, this.month, this.dayOfMonth)
    }
  }

//
//@BindingAdapter("local_date")
//fun setLocalDate(datePicker: DatePicker,value : LocalDate) {
//  if (getLocalDate(datePicker) != value) {
//    datePicker.updateDate(value.year, value.month.value, value.dayOfMonth)
//  }
//}
//
//@InverseBindingAdapter(attribute = "local_date")
//fun   getLocalDate(datePicker :DatePicker) : LocalDate ={
//  val day = datePicker.dayOfMonth
//  val month = datePicker.month + 1
//  val year = datePicker.year
//  LocalDate.of(year, month, day)
//}()
//
