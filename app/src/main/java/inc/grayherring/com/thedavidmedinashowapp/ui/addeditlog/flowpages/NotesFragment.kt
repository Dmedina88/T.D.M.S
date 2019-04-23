package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentNotesBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.EntryFlowViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class NotesFragment : BaseFragment() {

  lateinit var bindings: FragmentNotesBinding

  private val viewModel by sharedViewModel<EntryFlowViewModel>(from = { requireParentFragment() })

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    bindings = FragmentNotesBinding.inflate(inflater, container, false)

    bindings.vm = viewModel
    bindings.lifecycleOwner = viewLifecycleOwner

    return bindings.root
  }

}
