package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentEntryTypePickerBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.ViewModelFactory
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.EntryFlowViewModel
import inc.grayherring.com.thedavidmedinashowapp.util.ui.GridSpacingItemDecoration
import timber.log.Timber
import javax.inject.Inject

class EntryTypeFragment : BaseFragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  lateinit var bindings: FragmentEntryTypePickerBinding

  private val viewModel by lazy {
    Timber.d("EntryTypeFragment lazy")
    ViewModelProviders.of(this.requireParentFragment(), viewModelFactory)
      .get(EntryFlowViewModel::class.java)
  }

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
