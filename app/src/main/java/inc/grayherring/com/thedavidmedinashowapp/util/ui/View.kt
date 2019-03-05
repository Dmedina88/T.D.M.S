package inc.grayherring.com.thedavidmedinashowapp.util.ui

import android.view.View
import androidx.core.content.ContextCompat
import inc.grayherring.com.thedavidmedinashowapp.R

val View.colorPrimary get() = ContextCompat.getColor(this.context, R.color.colorPrimary)
val View.colorWhite get() = ContextCompat.getColor(this.context, R.color.white)