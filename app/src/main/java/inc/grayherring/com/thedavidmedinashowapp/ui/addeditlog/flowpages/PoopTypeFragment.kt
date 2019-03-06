package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentPoopTypePickerBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.ViewModelFactory
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.PoopFlowViewModel
import inc.grayherring.com.thedavidmedinashowapp.util.ui.GridSpacingItemDecoration
import javax.inject.Inject

class PoopTypeFragment : BaseFragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  lateinit var bindings: FragmentPoopTypePickerBinding

  private val viewModel by lazy {
    ViewModelProviders.of(this, viewModelFactory)
      .get(PoopFlowViewModel::class.java)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    val adapter = PoopTypeAdapter(viewModel::selectPoopType)
    bindings = FragmentPoopTypePickerBinding.inflate(inflater, container, false)
    bindings.poopTypeList.adapter = adapter
    bindings.poopTypeList.layoutManager = GridLayoutManager(requireContext(),2)
    bindings.poopTypeList.addItemDecoration(GridSpacingItemDecoration(2, 18, true))

    viewModel.poopTypeList.observe(viewLifecycleOwner, Observer {
      adapter.setData(it)
    })


    return bindings.root
  }

}
