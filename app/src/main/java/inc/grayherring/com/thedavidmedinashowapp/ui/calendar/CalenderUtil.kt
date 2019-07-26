package inc.grayherring.com.thedavidmedinashowapp.ui.calendar

import android.content.Context
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import org.threeten.bp.LocalDate
import org.threeten.bp.temporal.ChronoUnit
import timber.log.Timber

fun RecyclerView.configureForCalender(context: Context, calenderAdapter: CalenderAdapter) =
  this.apply {
    val gridlayoutMutableList = GridLayoutManager(context, 7)
    adapter = calenderAdapter
    layoutManager = gridlayoutMutableList
    addItemDecoration(
      DividerItemDecoration(
        context,
        DividerItemDecoration.HORIZONTAL
      )
    )
    addItemDecoration(
      DividerItemDecoration(
        context,
        DividerItemDecoration.VERTICAL
      )
    )

    gridlayoutMutableList.spanSizeLookup = object : SpanSizeLookup() {
      override fun getSpanSize(position: Int): Int {
        return when (calenderAdapter.data[position]) {
          is CalenderIteam.Month -> 7
          else -> 1
        }

      }
    }
  }


data class Event(val date: LocalDate, val message: String)

fun calenderPopulated(
  startDate: LocalDate,
  endDate: LocalDate,
  messages: List<Event>
): List<CalenderIteam> {

  val calenderItems = mutableListOf<CalenderIteam>()
  val numberOfMonths = ChronoUnit.MONTHS.between(startDate, endDate)

  for (monthsFromStart in 0..numberOfMonths) {
    val startOfMonth = startDate.plusMonths(monthsFromStart).withDayOfMonth(1)
    val month = CalenderIteam.Month("${startOfMonth.month} ${startOfMonth.year}")

    val eventsForByDaysInMonth = messages.filter { it.date.monthValue == startOfMonth.monthValue }
      .groupBy { it.date.dayOfMonth }

    calenderItems.add(month)
    calenderItems.addAll(
      listOf(
        CalenderIteam.DayOfWeek("Sun"),
        CalenderIteam.DayOfWeek("Mon"),
        CalenderIteam.DayOfWeek("Tues"),
        CalenderIteam.DayOfWeek("Wed"),
        CalenderIteam.DayOfWeek("Thur"),
        CalenderIteam.DayOfWeek("Friday"),
        CalenderIteam.DayOfWeek("Sat")
      )
    )

    // add blank days
    if (startOfMonth.dayOfWeek.value < 7) {
      for (day in 0 until startOfMonth.dayOfWeek.value) {
        Timber.d("${startOfMonth.month.name} $day ${startOfMonth.dayOfWeek.value}")
        calenderItems.add(CalenderIteam.Day("", "", CalenderInfo(0, 0, 0)))
      }
    }

    //add number days
    for (day in 1..startOfMonth.lengthOfMonth()) {
      val message =
        eventsForByDaysInMonth.getOrDefault(day, emptyList()).joinToString { it.message }
      calenderItems.add(
        CalenderIteam.Day(
          day.toString(),
          message,
          CalenderInfo(startOfMonth.year, startOfMonth.month.value, day)
        )
      )
    }
  }
  return calenderItems
}