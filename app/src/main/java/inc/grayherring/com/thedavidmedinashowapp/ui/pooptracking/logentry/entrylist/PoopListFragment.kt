package inc.grayherring.com.thedavidmedinashowapp.ui.pooptracking.logentry.entrylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import inc.grayherring.com.thedavidmedinashowapp.R
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentEntryListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PoopListFragment : BaseFragment() {

  private val viewModel by viewModel<PoopListVM>()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    val bindings = FragmentEntryListBinding.inflate(inflater, container, false)

    val adapter = EntryAdapter({
      val action = PoopListFragmentDirections.actionPoopListFragmentToLogDetailFragment(it.id)
      findNavController().navigate(action)
    }, { viewModel.dateClicked(it) })

    viewModel.entryItems.observe(viewLifecycleOwner) { adapter.submitList(it) }

    bindings.run {
      recyclerView.layoutManager = LinearLayoutManager(root.context)
      recyclerView.addItemDecoration(DividerItemDecoration(root.context, VERTICAL))
      recyclerView.adapter = adapter
      fab.setOnClickListener {
        findNavController().navigate(R.id.action_list_to_flow)
      }
    }

    return bindings.root

  }

}
