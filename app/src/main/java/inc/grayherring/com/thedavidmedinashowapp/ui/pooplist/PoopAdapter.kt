package inc.grayherring.com.thedavidmedinashowapp.ui.pooplist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import inc.grayherring.com.thedavidmedinashowapp.R
import inc.grayherring.com.thedavidmedinashowapp.data.PoopLog

class PoopAdapter() : RecyclerView.Adapter<PoopListVH>() {

    private val data = mutableListOf<PoopLog>()

    fun setData(newData: List<PoopLog>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoopListVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.poop_list_item, parent, false)
        return PoopListVH.Entry(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: PoopListVH, position: Int) {
        holder.bind(data[position])
    }

}


sealed class PoopListVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    class Entry(itemView: View) : PoopListVH(itemView) {
        val date: TextView = itemView.findViewById(R.id.date)
        val type: ImageView = itemView.findViewById(R.id.poop_type)

        override fun bind(poopLog: PoopLog) {
            date.text = poopLog.date.toString()
        }

    }

    abstract fun bind(poopLog: PoopLog)

}

