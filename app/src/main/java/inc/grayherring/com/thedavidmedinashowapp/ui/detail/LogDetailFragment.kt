package inc.grayherring.com.thedavidmedinashowapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import inc.grayherring.com.thedavidmedinashowapp.R
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentDetailsBinding
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentPoopFlowBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.ViewModelFactory
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.PoopFlowViewModel
import timber.log.Timber
import javax.inject.Inject

class LogDetailFragment : BaseFragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  lateinit var bindings: FragmentDetailsBinding

  private val viewModel by lazy {
    ViewModelProviders.of(this, viewModelFactory)
      .get(LogDetailViewModel::class.java)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    bindings = FragmentDetailsBinding.inflate(inflater, container, false)


    return bindings.root
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.save, menu)
    super.onCreateOptionsMenu(menu, inflater)
  }


}
