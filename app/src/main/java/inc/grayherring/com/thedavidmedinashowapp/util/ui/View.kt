package inc.grayherring.com.thedavidmedinashowapp.util.ui

import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import inc.grayherring.com.thedavidmedinashowapp.R

val View.colorPrimary get() = ContextCompat.getColor(this.context, R.color.colorPrimary)
val View.colorWhite get() = ContextCompat.getColor(this.context, R.color.white)

private fun View.rippleDrawable(@ColorInt color: Int? = null, borderless: Boolean = false): Drawable {
  val res =
    if (borderless) R.attr.selectableItemBackgroundBorderless else R.attr.selectableItemBackground
  val attrs = intArrayOf(res)
  val typedArray = context.obtainStyledAttributes(attrs)
  val selectableBackground = typedArray.getDrawable(0)
  typedArray.recycle()
  return if (color == null) {
    selectableBackground
  } else {
    val solidBackground = ShapeDrawable()
    solidBackground.paint.color = color
    LayerDrawable(arrayOf(solidBackground, selectableBackground))
  }
}

fun View.setRippleBackground(@ColorInt color: Int? = null, borderless: Boolean = false) {
  background = rippleDrawable(color, borderless)
}

fun View.setRippleForeground(@ColorInt color: Int? = null, borderless: Boolean = false) {
  foreground = rippleDrawable(color, borderless)
}

fun View.addRippleBackgroundLayer() {
  val attrs = intArrayOf(R.attr.selectableItemBackground)
  val typedArray = context.obtainStyledAttributes(attrs)
  val selectableBackground = typedArray.getDrawable(0)
  typedArray.recycle()
  this.background = LayerDrawable(arrayOf(background, selectableBackground))
}