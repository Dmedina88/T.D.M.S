package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentTakePhotoBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.ViewModelFactory
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.PoopFlowViewModel
import javax.inject.Inject

class PhotoFragment : BaseFragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  lateinit var bindings: FragmentTakePhotoBinding

  private val viewModel by lazy {
    ViewModelProviders.of(this, viewModelFactory)
      .get(PoopFlowViewModel::class.java)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    bindings = FragmentTakePhotoBinding.inflate(inflater, container, false)


    return bindings.root
  }

}
