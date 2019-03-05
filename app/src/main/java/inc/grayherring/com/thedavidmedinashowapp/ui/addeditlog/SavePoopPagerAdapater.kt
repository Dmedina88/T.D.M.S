package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages.DatePickerFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages.NotesFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages.PhotoFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages.PoopTypeFragment

class SavePoopPagerAdapater(fragmentManager: FragmentManager) :
  FragmentPagerAdapter(fragmentManager) {
  override fun getItem(position: Int): Fragment = when (position) {
    0 -> DatePickerFragment()
    1 -> NotesFragment()
    2 -> PoopTypeFragment()
    3 -> PhotoFragment()
    else -> error("No View for position $position")

  }

  override fun getCount() = 4

}
