package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import inc.grayherring.com.thedavidmedinashowapp.data.models.PoopType
import inc.grayherring.com.thedavidmedinashowapp.data.models.icon
import inc.grayherring.com.thedavidmedinashowapp.data.models.stringRes
import inc.grayherring.com.thedavidmedinashowapp.databinding.PoopTypeItemBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.PoopTypeItem
import inc.grayherring.com.thedavidmedinashowapp.util.ui.DataBindingViewHolder
import inc.grayherring.com.thedavidmedinashowapp.util.ui.colorPrimary
import inc.grayherring.com.thedavidmedinashowapp.util.ui.colorWhite

typealias TypeClicked = ((PoopType) -> Unit)

class PoopTypeAdapter(private val typeClicked: TypeClicked) : RecyclerView.Adapter<PoopTypeVH>() {

  private val data = mutableListOf<PoopTypeItem>()

  fun setData(newData: List<PoopTypeItem>) {
    data.clear()
    data.addAll(newData)
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoopTypeVH =
    PoopTypeVH(PoopTypeItemBinding.inflate(LayoutInflater.from(parent.context)),typeClicked)

  override fun getItemCount() = data.size

  override fun onBindViewHolder(holder: PoopTypeVH, position: Int) {
   holder.bind(data[position])
  }

}


class PoopTypeVH(binding: PoopTypeItemBinding,val typeClicked: TypeClicked) : DataBindingViewHolder<PoopTypeItemBinding, PoopTypeItem>(binding) {
  override fun bind(item: PoopTypeItem) {
    binding.icon.setImageResource(item.poopType.icon)
    binding.detail.text= binding.root.context.getText(item.poopType.stringRes)
    binding.root.setBackgroundColor(if (item.selected) binding.root.colorPrimary else binding.root.colorWhite )
    binding.root.setOnClickListener {
      typeClicked(item.poopType)
    }
  }

}

