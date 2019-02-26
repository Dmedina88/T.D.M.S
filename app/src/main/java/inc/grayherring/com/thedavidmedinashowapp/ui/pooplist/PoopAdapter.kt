package inc.grayherring.com.thedavidmedinashowapp.ui.pooplist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import inc.grayherring.com.thedavidmedinashowapp.R
import inc.grayherring.com.thedavidmedinashowapp.data.PoopLog
import inc.grayherring.com.thedavidmedinashowapp.data.getIcon
import java.util.*

typealias PoopLogClicked = ((PoopLog) -> Unit)
typealias DateItemClicked = ((Calendar) -> Unit)

class PoopAdapter(val poopLogClicked: PoopLogClicked) : RecyclerView.Adapter<PoopListVH>() {

    private val data = mutableListOf<PoopListItem>()


    fun setData(newData: List<PoopListItem>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoopListVH =
        when (viewType) {
            0 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.poop_list_item, parent, false)
                PoopListVH.LogVH(view, poopLogClicked)
            }
            1 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.list_date_item, parent, false)
                PoopListVH.DateVH(view)
            }
            else -> error("invalid viewType")
        }


    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: PoopListVH, position: Int) {
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

sealed class PoopListVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    class LogVH(
        itemView: View,
        private val poopLogClicked: PoopLogClicked
    ) : PoopListVH(itemView) {
        val notes: TextView = itemView.findViewById(R.id.notes)
        val type: ImageView = itemView.findViewById(R.id.poop_type)

        fun bind(log: PoopListItem.Log) {

            notes.text = "${log.poopLog.notes} "
            type.setImageResource(log.poopLog.poopType.getIcon)
            itemView.setOnClickListener {
                poopLogClicked.invoke(log.poopLog)
            }
        }
    }

    class DateVH(
        itemView: View
    ) : PoopListVH(itemView) {
        val date: TextView = itemView.findViewById(R.id.date)

        fun bind(item: PoopListItem.Date) {
            date.text = item.date
            itemView.setOnClickListener {
                //  date?.invoke(Calendar)
            }
        }
    }
}
