package inc.grayherring.com.thedavidmedinashowapp.ui.calendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import inc.grayherring.com.thedavidmedinashowapp.databinding.DayBinding
import inc.grayherring.com.thedavidmedinashowapp.databinding.MonthIteamBinding
import inc.grayherring.com.thedavidmedinashowapp.databinding.WeekDayBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.calendar.CalenderIteam.Day
import inc.grayherring.com.thedavidmedinashowapp.ui.calendar.CalenderIteam.DayOfWeek
import inc.grayherring.com.thedavidmedinashowapp.ui.calendar.CalenderIteam.Month

sealed class CalenderIteam {
  data class Month(val name: String, val calenderInfo : CalenderInfo) : CalenderIteam()
  data class DayOfWeek(val name: String) : CalenderIteam()
  data class Day(val dayNumber: String, val message : String ="", val calenderInfo : CalenderInfo) : CalenderIteam()
}

data class CalenderInfo(val year: Int,val month: Int,  val day:Int)
typealias DateClickedListener  = ((CalenderInfo) -> Unit)

class CalenderAdapter(val onDayClicked: DateClickedListener) :
  RecyclerView.Adapter<CalenderViewHolder<ViewDataBinding, CalenderIteam>>() {

  val data: MutableList<CalenderIteam> = mutableListOf()

  fun setData(items: List<CalenderIteam>) {
    data.clear()
    data.addAll(items)
    notifyDataSetChanged()
  }

  override fun getItemCount(): Int = data.size

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): CalenderViewHolder<ViewDataBinding, CalenderIteam> =
    when (viewType) {
      0 -> {
        CalenderViewHolder.DayViewHolder(
          DayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
          ),
          onDayClicked
        )
      }
      1 -> {
        CalenderViewHolder.WeekDayViewHolder(
          WeekDayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
          )
        )
      }
      2 -> {
        CalenderViewHolder.MonthViewHolder(
          MonthIteamBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
          )
        )
      }
      else -> error("invalid viewType")
    } as CalenderViewHolder<ViewDataBinding, CalenderIteam>

  override fun onBindViewHolder(
    holder: CalenderViewHolder<ViewDataBinding, CalenderIteam>,
    position: Int
  ) {
    val item = data[position]
    when (item){
      is Day ->  holder.binds(item)
      is Month -> holder.binds(item)
      is DayOfWeek -> holder.binds(item)
    }
    holder.binds(item)

  }

  override fun getItemViewType(position: Int): Int = when (data[position]) {
    is CalenderIteam.Day -> 0
    is CalenderIteam.DayOfWeek -> 1
    is CalenderIteam.Month -> 2
  }
}

sealed class CalenderViewHolder<Binding : ViewDataBinding, CalenderItem : CalenderIteam>(protected val bindings: Binding) :
  RecyclerView.ViewHolder(bindings.root) {

  abstract fun binds(calenderIteam: CalenderItem)

  class MonthViewHolder(bindings: MonthIteamBinding) :
    CalenderViewHolder<MonthIteamBinding, Month>(bindings) {
    override fun binds(calenderIteam: Month) {
      bindings.month.text = calenderIteam.name
    }

  }

  class WeekDayViewHolder(bindings: WeekDayBinding) :
    CalenderViewHolder<WeekDayBinding, DayOfWeek>(bindings) {

    override fun binds(calenderIteam: DayOfWeek) {
      bindings.day.text = calenderIteam.name
    }
  }

  class DayViewHolder(bindings: DayBinding , val onDayClicked: DateClickedListener) :
    CalenderViewHolder<DayBinding, Day>(bindings) {
    override fun binds(calenderIteam: Day) {
      bindings.day.text = calenderIteam.dayNumber
      bindings.dayMessage.text = calenderIteam.message
      bindings.root.setOnClickListener {
        onDayClicked(calenderIteam.calenderInfo)
      }
    }
  }

}

