package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import inc.grayherring.com.thedavidmedinashowapp.R
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentEntryFlowBinding
import inc.grayherring.com.thedavidmedinashowapp.util.ui.snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class EntryFlowFragment : BaseFragment() {


  lateinit var bindings: FragmentEntryFlowBinding

  private val viewModel by viewModel<EntryFlowViewModel>()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    bindings = FragmentEntryFlowBinding.inflate(inflater, container, false)
    viewModel.init(EntryFlowFragmentArgs.fromBundle(arguments!!).id)
    val adapter = SaveEntryPager(childFragmentManager, requireContext())
    bindings.viewPager.adapter = adapter
    //sort of want to do this with data binding if i can
    bindings.tabLayout.setupWithViewPager(bindings.viewPager)

    setHasOptionsMenu(true)

    viewModel.run {
      finish.observe(viewLifecycleOwner, Observer {
        if (it) {
          findNavController().popBackStack()
        }
      })
      errors.observe(viewLifecycleOwner, Observer {
        it?.let {
          bindings.root.snackbar(R.string.mandatory_poop_type)
          bindings.viewPager.currentItem = 0
        }
      })
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
