package inc.grayherring.com.thedavidmedinashowapp.ui.entrylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import inc.grayherring.com.thedavidmedinashowapp.data.models.Entry
import inc.grayherring.com.thedavidmedinashowapp.data.models.detailRes
import inc.grayherring.com.thedavidmedinashowapp.data.models.icon
import inc.grayherring.com.thedavidmedinashowapp.data.models.nameRes
import inc.grayherring.com.thedavidmedinashowapp.databinding.DateItemBinding
import inc.grayherring.com.thedavidmedinashowapp.databinding.EntryListItemBinding

typealias EntryLogClicked = ((Entry) -> Unit)
typealias DateItemClicked = ((Int) -> Unit)

class EntryAdapter(
  private val poopLogClicked: EntryLogClicked,
  private val dateItemClicked: DateItemClicked
) :
  RecyclerView.Adapter<EntryListVH<ViewDataBinding>>() {

  private val data = mutableListOf<EntryListItem>()

  //todo : dif util
  fun setData(newData: List<EntryListItem>) {
    data.clear()
    data.addAll(newData)
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryListVH<ViewDataBinding> =
    when (viewType) {
      0 -> {
        EntryListVH.LogVH(
          EntryListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
          ), poopLogClicked
        )
      }
      1 -> {
        EntryListVH.DateVH(
          DateItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
          ), dateItemClicked
        )
      }
      else -> error("invalid viewType")
    } as EntryListVH<ViewDataBinding>

  override fun getItemCount() = data.size

  override fun onBindViewHolder(holder: EntryListVH<ViewDataBinding>, position: Int) {
    val item = data[position]
    when (item) {
      is EntryListItem.Log -> (holder as EntryListVH.LogVH).bind(item)
      is EntryListItem.Date -> (holder as EntryListVH.DateVH).bind(item)
    }
  }

  override fun getItemViewType(position: Int): Int = when (data[position]) {
    is EntryListItem.Log -> 0
    is EntryListItem.Date -> 1
  }
}

sealed class EntryListItem {
  data class Log(val entry: inc.grayherring.com.thedavidmedinashowapp.data.models.Entry) :
    EntryListItem()

  data class Date(val date: String) : EntryListItem()
}

sealed class EntryListVH<Binding : ViewDataBinding>(protected val bindings: Binding) :
  RecyclerView.ViewHolder(bindings.root) {

  class LogVH(bindings: EntryListItemBinding, private val poopLogClicked: EntryLogClicked) :
    EntryListVH<EntryListItemBinding>(bindings) {

    fun bind(item: EntryListItem.Log) {

      val info1 =
        if (item.entry.name.isNotBlank()) item.entry.name else this.itemView.context.getString(item.entry.poopType.nameRes)
      val info2 =
        if (item.entry.notes.isNotBlank()) item.entry.notes else this.itemView.context.getString(
          item.entry.poopType.detailRes
        )
      bindings.info1.text = info1
      bindings.info2.text = info2
      bindings.icon.setImageResource(item.entry.poopType.icon)
      itemView.setOnClickListener {
        poopLogClicked.invoke(item.entry)
      }
    }
  }

  class DateVH(bindings: DateItemBinding, private val dateItemClicked: DateItemClicked) :
    EntryListVH<DateItemBinding>(bindings) {

    fun bind(item: EntryListItem.Date) {
      bindings.date.text = item.date
      bindings.realRoot.setOnClickListener {
        dateItemClicked.invoke(this.layoutPosition)
      }
    }
  }
}
