package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.whenCreated
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentNotesBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.EntryFlowViewModel
import inc.grayherring.com.thedavidmedinashowapp.util.ui.textChangeFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

@ExperimentalCoroutinesApi
@FlowPreview
class NotesFragment : BaseFragment() {

  lateinit var bindings: FragmentNotesBinding

  private val viewModel by sharedViewModel<EntryFlowViewModel>(from = this::requireParentFragment )

  @ObsoleteCoroutinesApi
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    bindings = FragmentNotesBinding.inflate(inflater, container, false)

    bindings.vm = viewModel
    bindings.lifecycleOwner = viewLifecycleOwner


    lifecycleScope.launch {

      bindings.nameEditText.textChangeFlow(lifecycleScope).consumeEach {
        Timber.d(it)
        viewModel.name.value = it }
    }

    return bindings.root
  }

}
