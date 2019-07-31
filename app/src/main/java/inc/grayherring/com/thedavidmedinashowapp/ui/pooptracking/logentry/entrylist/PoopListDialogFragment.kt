package inc.grayherring.com.thedavidmedinashowapp.ui.pooptracking.logentry.entrylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import inc.grayherring.com.thedavidmedinashowapp.R.style
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentEntryListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PoopListDialogFragment : DialogFragment() {

  private val viewModel by viewModel<PoopListVM>()
  private val  args : PoopListDialogFragmentArgs by navArgs()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    val bindings = FragmentEntryListBinding.inflate(inflater, container, false)

    val adapter = EntryAdapter({
      val action = PoopListDialogFragmentDirections.actionMyDialogToLogDetailFragment(it.id)
      findNavController().navigate(action)
    }, { viewModel.dateClicked(it) })

    viewModel.getEntryItems(args.epochDay).observe(viewLifecycleOwner) { adapter.submitList(it) }

    bindings.run {
      recyclerView.layoutManager = LinearLayoutManager(root.context)
      recyclerView.addItemDecoration(DividerItemDecoration(ContextThemeWrapper(requireContext(), style.AppTheme), VERTICAL))
      recyclerView.adapter = adapter
      fab.setOnClickListener {
        findNavController().navigate(PoopListFragmentDirections.actionListToFlow(epochDay = args.epochDay))
      }
    }

    return bindings.root

  }

}
