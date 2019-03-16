package inc.grayherring.com.thedavidmedinashowapp.util.ui

import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.RippleDrawable
import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

val View.colorPrimary
  get() = ContextCompat.getColor(
    this.context,
    inc.grayherring.com.thedavidmedinashowapp.R.color.colorPrimary
  )
val View.colorWhite
  get() = ContextCompat.getColor(
    this.context,
    inc.grayherring.com.thedavidmedinashowapp.R.color.white
  )


fun getPressedColorRippleDrawable(normalColor: Int, pressedColor: Int): RippleDrawable {
  return RippleDrawable(
    getPressedColorSelector(normalColor, pressedColor),
    getColorDrawableFromColor(normalColor),
    null
  )
}

fun getPressedColorSelector(normalColor: Int, pressedColor: Int): ColorStateList {
  return ColorStateList(
    arrayOf(
      intArrayOf(android.R.attr.state_pressed),
      intArrayOf(android.R.attr.state_focused),
      intArrayOf(android.R.attr.state_activated),
      intArrayOf()
    ),
    intArrayOf(pressedColor, pressedColor, pressedColor, normalColor)
  )
}

fun getColorDrawableFromColor(color: Int): ColorDrawable {
  return ColorDrawable(color)
}

fun View.setRippleBackgroundColor(normalColor: Int, pressedColor: Int = this.colorWhite) {
  this.background = getPressedColorRippleDrawable(normalColor, pressedColor)
}

fun View.snackbar(text: CharSequence, duration: Int = Snackbar.LENGTH_SHORT): Snackbar {
  val snack = Snackbar.make(this, text, duration)
  snack.show()
  return snack
}

fun View.snackbar(@StringRes text: Int, duration: Int = Snackbar.LENGTH_SHORT): Snackbar {
  val snack = Snackbar.make(this, text, duration)
  snack.show()
  return snack
}
