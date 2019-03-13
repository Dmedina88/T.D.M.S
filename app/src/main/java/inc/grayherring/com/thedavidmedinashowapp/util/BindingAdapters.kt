package inc.grayherring.com.thedavidmedinashowapp.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@set:BindingAdapter("visibleOrGone")
var View.visibleOrGone
  get() = visibility == View.VISIBLE
  set(value) {
    visibility = if (value) View.VISIBLE else View.GONE
  }

@set:BindingAdapter("visible")
var View.visible
  get() = visibility == View.VISIBLE
  set(value) {
    visibility = if (value) View.VISIBLE else View.INVISIBLE
  }

@BindingAdapter("imagePath")
fun ImageView.loadImageFromPath(path: String) {
  Glide.with(this.context)
    .load(path)
    .centerCrop()
    .into(this)
}