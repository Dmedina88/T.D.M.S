package inc.grayherring.com.thedavidmedinashowapp.ui.pooptracking.addeditlog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import inc.grayherring.com.thedavidmedinashowapp.R
import inc.grayherring.com.thedavidmedinashowapp.R.string
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentPagerWithTabsBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.pooptracking.addeditlog.flowpages.DatePickerFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.pooptracking.addeditlog.flowpages.NotesFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.pooptracking.addeditlog.flowpages.PhotoFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.pooptracking.addeditlog.flowpages.PoopTypeFragment
import inc.grayherring.com.thedavidmedinashowapp.util.ui.SimplePagerAdapter
import inc.grayherring.com.thedavidmedinashowapp.util.ui.SimplePagerItem
import inc.grayherring.com.thedavidmedinashowapp.util.ui.snackbar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PoopFlowFragment : BaseFragment() {

  lateinit var bindings: FragmentPagerWithTabsBinding

  private val viewModel by viewModel<PoopFlowViewModel>()

  @ExperimentalCoroutinesApi
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    bindings = FragmentPagerWithTabsBinding.inflate(inflater, container, false)
    val args = PoopFlowFragmentArgs.fromBundle(arguments!!)
    viewModel.init(args.id, args.epochDay)
    val adapter = SimplePagerAdapter(
      childFragmentManager, requireContext(), mutableListOf(
        SimplePagerItem(PoopTypeFragment(), string.type_title),
        SimplePagerItem(DatePickerFragment(), string.date_title),
        SimplePagerItem(NotesFragment(), string.note_title),
        SimplePagerItem(PhotoFragment(), string.photo_title)
      )
    )
    bindings.viewPager.adapter = adapter
    //sort of want to do this with data binding if i can
    bindings.tabLayout.setupWithViewPager(bindings.viewPager)

    setHasOptionsMenu(true)

      lifecycleScope.launch {
        viewModel.finish.collect {
          findNavController().popBackStack()
        }
      }
    lifecycleScope.launch {
        viewModel.errors.collect {
          bindings.root.snackbar(R.string.mandatory_poop_type)
          bindings.viewPager.currentItem = 0
        }
      }


    return bindings.root
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.save, menu)
    super.onCreateOptionsMenu(menu, inflater)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.action_save) {
      viewModel.save()
    }
    return super.onOptionsItemSelected(item)
  }
}
