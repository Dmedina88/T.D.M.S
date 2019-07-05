package inc.grayherring.com.core.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import inc.grayherring.com.core.R
import inc.grayherring.com.core.models.EntryType.TYPE_1
import inc.grayherring.com.core.models.EntryType.TYPE_2
import inc.grayherring.com.core.models.EntryType.TYPE_3
import inc.grayherring.com.core.models.EntryType.TYPE_4
import inc.grayherring.com.core.models.EntryType.TYPE_5
import inc.grayherring.com.core.models.EntryType.TYPE_6
import inc.grayherring.com.core.models.EntryType.TYPE_7

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
    TYPE_1 -> R.drawable.abc_ab_share_pack_mtrl_alpha
    TYPE_2 -> R.drawable.abc_btn_check_material
    TYPE_3 -> R.drawable.abc_btn_check_to_on_mtrl_000
    TYPE_4 -> R.drawable.abc_btn_default_mtrl_shape
    TYPE_5 -> R.drawable.abc_btn_radio_material
    TYPE_6 -> R.drawable.abc_spinner_textfield_background_material
    TYPE_7 -> R.drawable.abc_cab_background_internal_bg
  }