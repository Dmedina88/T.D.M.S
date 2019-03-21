package inc.grayherring.com.thedavidmedinashowapp.ui.pooplist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import inc.grayherring.com.thedavidmedinashowapp.data.models.PoopLog
import inc.grayherring.com.thedavidmedinashowapp.data.models.icon
import inc.grayherring.com.thedavidmedinashowapp.data.models.stringRes
import inc.grayherring.com.thedavidmedinashowapp.databinding.DateItemBinding
import inc.grayherring.com.thedavidmedinashowapp.databinding.PoopListItemBinding
import org.threeten.bp.format.DateTimeFormatter

typealias PoopLogClicked = ((PoopLog) -> Unit)
typealias DateItemClicked = ((Int) -> Unit)

class PoopAdapter(private val poopLogClicked: PoopLogClicked, dateItemClicked: DateItemClicked) :
  RecyclerView.Adapter<PoopListVH<ViewDataBinding>>() {

  private val data = mutableListOf<PoopListItem>()

  //todo : dif util
  fun setData(newData: List<PoopListItem>) {
    data.clear()
    data.addAll(newData)
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoopListVH<ViewDataBinding> =
    when (viewType) {
      0 -> {
        PoopListVH.LogVH(
          PoopListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
          ), poopLogClicked
        )
      }
      1 -> {
        PoopListVH.DateVH(
          DateItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
          ), {})
      }
      else -> error("invalid viewType")
    } as PoopListVH<ViewDataBinding>

  override fun getItemCount() = data.size

  override fun onBindViewHolder(holder: PoopListVH<ViewDataBinding>, position: Int) {
    val item = data[position]
    when (item) {
      is PoopListItem.Log -> (holder as PoopListVH.LogVH).bind(item)
      is PoopListItem.Date -> (holder as PoopListVH.DateVH).bind(item)
    }
  }

  override fun getItemViewType(position: Int): Int = when (data[position]) {
    is PoopListItem.Log -> 0
    is PoopListItem.Date -> 1
  }
}

sealed class PoopListItem {
  data class Log(val poopLog: PoopLog) : PoopListItem()
  data class Date(val date: String) : PoopListItem()
}

sealed class PoopListVH<Binding : ViewDataBinding>(protected val bindings: Binding) :
  RecyclerView.ViewHolder(bindings.root) {

  class LogVH(bindings: PoopListItemBinding, private val poopLogClicked: PoopLogClicked) :
    PoopListVH<PoopListItemBinding>(bindings) {

    fun bind(item: PoopListItem.Log) {

      val info1 = if (item.poopLog.name.isNotBlank()) item.poopLog.name else item.poopLog.poopType.name
      val info2= if (item.poopLog.notes.isNotBlank()) item.poopLog.notes else this.itemView.context.getString(item.poopLog.poopType.stringRes)
      bindings.info1.text = info1
      bindings.info2.text = info2
      bindings.icon.setImageResource(item.poopLog.poopType.icon)
      itemView.setOnClickListener {
        poopLogClicked.invoke(item.poopLog)
      }
    }
  }

  class DateVH(bindings: DateItemBinding, private val dateItemClicked: DateItemClicked) :
    PoopListVH<DateItemBinding>(bindings) {

    fun bind(item: PoopListItem.Date) {
      bindings.date.text = item.date
      bindings.realRoot.setOnClickListener {
        dateItemClicked.invoke(this.layoutPosition)
      }
    }
  }
}
