package inc.grayherring.com.thedavidmedinashowapp.data.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import inc.grayherring.com.thedavidmedinashowapp.R
import inc.grayherring.com.thedavidmedinashowapp.data.models.EntryType.TYPE_1
import inc.grayherring.com.thedavidmedinashowapp.data.models.EntryType.TYPE_2
import inc.grayherring.com.thedavidmedinashowapp.data.models.EntryType.TYPE_3
import inc.grayherring.com.thedavidmedinashowapp.data.models.EntryType.TYPE_4
import inc.grayherring.com.thedavidmedinashowapp.data.models.EntryType.TYPE_5
import inc.grayherring.com.thedavidmedinashowapp.data.models.EntryType.TYPE_6
import inc.grayherring.com.thedavidmedinashowapp.data.models.EntryType.TYPE_7

enum class EntryType {
  TYPE_1,
  TYPE_2,
  TYPE_3,
  TYPE_4,
  TYPE_5,
  TYPE_6,
  TYPE_7,
}

val EntryType.detailRes
  @StringRes
  get() = when (this) {
    TYPE_1 -> R.string.poop_type_1
    TYPE_2 -> R.string.poop_type_2
    TYPE_3 -> R.string.poop_type_3
    TYPE_4 -> R.string.poop_type_4
    TYPE_5 -> R.string.poop_type_5
    TYPE_6 -> R.string.poop_type_6
    TYPE_7 -> R.string.poop_type_7
  }

val EntryType.nameRes
  @StringRes
  get() = when (this) {
    TYPE_1 -> R.string.poop_type_1_name
    TYPE_2 -> R.string.poop_type_2_name
    TYPE_3 -> R.string.poop_type_3_name
    TYPE_4 -> R.string.poop_type_4_name
    TYPE_5 -> R.string.poop_type_5_name
    TYPE_6 -> R.string.poop_type_6_name
    TYPE_7 -> R.string.poop_type_7_name
  }


val EntryType.icon
  @DrawableRes
  get() = when (this) {
    TYPE_1 -> R.drawable.ic_launcher_background
    TYPE_2 -> R.drawable.ic_launcher_foreground
    TYPE_3 -> R.drawable.ic_menu_gallery
    TYPE_4 -> R.drawable.ic_menu_send
    TYPE_5 -> R.drawable.ic_menu_slideshow
    TYPE_6 -> R.drawable.ic_menu_manage
    TYPE_7 -> R.drawable.ic_menu_camera
  }