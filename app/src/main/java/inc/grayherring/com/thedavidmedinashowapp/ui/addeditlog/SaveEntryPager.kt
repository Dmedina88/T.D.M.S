package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import inc.grayherring.com.thedavidmedinashowapp.R
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages.DatePickerFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages.EntryTypeFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages.NotesFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages.PhotoFragment

data class FlowItems(val fragment: Fragment, @StringRes val title: Int)
class SaveEntryPager(fragmentManager: FragmentManager, val context: Context) :
  FragmentPagerAdapter(fragmentManager) {

  val data = mutableListOf(
    FlowItems(EntryTypeFragment(), R.string.type_title),
    FlowItems(DatePickerFragment(), R.string.date_title),
    FlowItems(NotesFragment(), R.string.note_title),
    FlowItems(PhotoFragment(), R.string.photo_title)
    )

  override fun getItem(position: Int): Fragment =
    data[position].fragment

  override fun getCount() = data.size

  override fun getPageTitle(position: Int): CharSequence? =
   context.getString( data[position].title)

}
