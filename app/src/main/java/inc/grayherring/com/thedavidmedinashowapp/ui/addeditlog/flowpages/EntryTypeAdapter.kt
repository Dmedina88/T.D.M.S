package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import inc.grayherring.com.thedavidmedinashowapp.data.models.EntryType
import inc.grayherring.com.thedavidmedinashowapp.data.models.detailRes
import inc.grayherring.com.thedavidmedinashowapp.data.models.icon
import inc.grayherring.com.thedavidmedinashowapp.databinding.EntryTypeItemBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.EntryTypeItem
import inc.grayherring.com.thedavidmedinashowapp.util.ui.colorPrimary
import inc.grayherring.com.thedavidmedinashowapp.util.ui.colorWhite
import inc.grayherring.com.thedavidmedinashowapp.util.ui.setRippleBackgroundColor

typealias TypeClicked = ((EntryType) -> Unit)

class EntryTypeAdapter(private val typeClicked: TypeClicked) : RecyclerView.Adapter<EntryTypeVH>() {

  private val data = mutableListOf<EntryTypeItem>()

  fun setData(newData: List<EntryTypeItem>) {
    data.clear()
    data.addAll(newData)
    notifyDataSetChanged()

  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryTypeVH =
  EntryTypeVH(EntryTypeItemBinding.inflate(LayoutInflater.from(parent.context),parent,false), typeClicked)

  override fun getItemCount() = data.size

  override fun onBindViewHolder(holder: EntryTypeVH, position: Int) {
    holder.bind(data[position])
  }

}

class EntryTypeVH(val binding: EntryTypeItemBinding, val typeClicked: TypeClicked) : RecyclerView.ViewHolder(binding.root) {
   fun bind(item: EntryTypeItem) {
    binding.icon.setImageResource(item.poopType.icon)
    binding.detail.text = binding.root.context.getText(item.poopType.detailRes)
    if (item.selected) {
      binding.root.setRippleBackgroundColor(binding.root.colorPrimary, binding.root.colorWhite)
    } else {
      binding.root.setRippleBackgroundColor(binding.root.colorWhite, binding.root.colorPrimary)
    }
    binding.root.setOnClickListener {
      typeClicked(item.poopType)
    }
  }

}

