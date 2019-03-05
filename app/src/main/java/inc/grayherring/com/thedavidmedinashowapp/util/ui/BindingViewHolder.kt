package inc.grayherring.com.thedavidmedinashowapp.util.ui

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

//unsure if worth it
abstract class DataBindingViewHolder<Binding : ViewDataBinding,Item>(protected val binding: Binding) : RecyclerView.ViewHolder(binding.root) {

 abstract fun bind(item: Item)
}