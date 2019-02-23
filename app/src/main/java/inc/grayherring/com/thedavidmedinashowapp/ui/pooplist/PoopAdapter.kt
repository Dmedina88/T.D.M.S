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
import java.text.SimpleDateFormat

val format1 = SimpleDateFormat("yyyy-MM-dd")
typealias PoopLogClicked = ((PoopLog) -> Unit)

class PoopAdapter(val poopLogClicked: PoopLogClicked) : RecyclerView.Adapter<PoopListVH>() {

    private val data = mutableListOf<PoopLog>()

    fun setData(newData: List<PoopLog>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoopListVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.poop_list_item, parent, false)
        return PoopListVH.Entry(view, poopLogClicked)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: PoopListVH, position: Int) {
        holder.bind(data[position])
    }

}


sealed class PoopListVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    class Entry(itemView: View, val onPoopLogClicked: PoopLogClicked) : PoopListVH(itemView) {
        val date: TextView = itemView.findViewById(R.id.date)
        val type: ImageView = itemView.findViewById(R.id.poop_type)

        override fun bind(poopLog: PoopLog) {
            date.text = "${format1.format(poopLog.date.time)} ${poopLog.notes} "
            type.setImageResource(poopLog.poopType.getIcon)
            itemView.setOnClickListener {
                onPoopLogClicked(poopLog)
            }
        }
    }

    abstract fun bind(poopLog: PoopLog)

}

