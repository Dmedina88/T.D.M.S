package inc.grayherring.com.thedavidmedinashowapp.ui.logentry.entrylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import inc.grayherring.com.thedavidmedinashowapp.R
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentEntryListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneOffset
import java.time.LocalDateTime

class EntryListDialogFragment : DialogFragment() {

  private val viewModel by viewModel<EntryListVM>()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    val bindings = FragmentEntryListBinding.inflate(inflater, container, false)

    val adapter = EntryAdapter({
      val action = EntryListDialogFragmentDirections.actionMyDialogToLogDetailFragment(it.id)
      findNavController().navigate(action)
    }, { viewModel.dateClicked(it) })

    viewModel.getEntryItems(LocalDate.now().toEpochDay()).observe(viewLifecycleOwner) { adapter.submitList(it) }

    bindings.run {
      recyclerView.layoutManager = LinearLayoutManager(root.context)
      recyclerView.addItemDecoration(DividerItemDecoration(root.context, VERTICAL))
      recyclerView.adapter = adapter
      fab.setOnClickListener {
        findNavController().navigate(R.id.action_my_dialog_to_poopFlowFragment)
      }
    }

    return bindings.root

  }

}
