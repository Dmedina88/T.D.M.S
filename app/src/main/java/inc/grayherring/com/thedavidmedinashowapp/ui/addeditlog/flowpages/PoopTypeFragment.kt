package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentPoopFlowBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.ViewModelFactory
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.edit.EditPoopLogViewModel
import javax.inject.Inject

class PoopTypeFragment : BaseFragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  lateinit var bindings: FragmentPoopFlowBinding

  private val viewModel by lazy {
    ViewModelProviders.of(this, viewModelFactory)
      .get(EditPoopLogViewModel::class.java)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    bindings = FragmentPoopFlowBinding.inflate(inflater, container, false)


    return bindings.root
  }

}
