package inc.grayherring.com.thedavidmedinashowapp.ui.pooptracking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import inc.grayherring.com.thedavidmedinashowapp.R
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentPagerWithTabsBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.pooptracking.logentry.entrycalender.PoopCalenderFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.pooptracking.logentry.entrylist.PoopListFragment
import inc.grayherring.com.thedavidmedinashowapp.util.ui.SimplePagerAdapter
import inc.grayherring.com.thedavidmedinashowapp.util.ui.SimplePagerItem

class PoopTrackerHomeFragment : BaseFragment() {

  private lateinit var bindings: FragmentPagerWithTabsBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    bindings = FragmentPagerWithTabsBinding.inflate(inflater, container, false)

    val adapter = SimplePagerAdapter(
      childFragmentManager, requireContext(), mutableListOf(
        SimplePagerItem(PoopListFragment(), R.string.poop_tracker),
        SimplePagerItem(PoopCalenderFragment(), R.string.poop_tracker)
      )
    )
    //sort of want to do this with data binding if i can
    bindings.viewPager.adapter = adapter
    bindings.tabLayout.setupWithViewPager(bindings.viewPager)

    return bindings.root

  }

}

