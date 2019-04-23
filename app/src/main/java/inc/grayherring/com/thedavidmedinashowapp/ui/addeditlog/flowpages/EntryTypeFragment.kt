package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentEntryTypePickerBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.EntryFlowViewModel
import inc.grayherring.com.thedavidmedinashowapp.util.ui.GridSpacingItemDecoration
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class EntryTypeFragment : BaseFragment() {

  lateinit var bindings: FragmentEntryTypePickerBinding

  private val viewModel by sharedViewModel<EntryFlowViewModel>(from = this::requireParentFragment)

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val adapter = EntryTypeAdapter(viewModel::selectEntryType)

    bindings = FragmentEntryTypePickerBinding.inflate(inflater, container, false)
    bindings.poopTypeList.adapter = adapter
    bindings.poopTypeList.layoutManager = GridLayoutManager(requireContext(), 2)
    bindings.poopTypeList.addItemDecoration(GridSpacingItemDecoration(2, 18, true))

    viewModel.poopTypeList.observe(viewLifecycleOwner, Observer {
      Timber.i(it.toString())
      adapter.setData(it)
    })

    return bindings.root
  }

}
