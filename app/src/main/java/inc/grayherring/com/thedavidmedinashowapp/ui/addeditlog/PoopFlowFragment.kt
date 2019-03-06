package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentPoopFlowBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.ViewModelFactory
import javax.inject.Inject

class PoopFlowFragment : BaseFragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  lateinit var bindings: FragmentPoopFlowBinding

  private val viewModel by lazy {
    ViewModelProviders.of(this, viewModelFactory)
      .get(PoopFlowViewModel::class.java)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    bindings = FragmentPoopFlowBinding.inflate(inflater, container, false)
    viewModel.init(null)
    bindings.viewPager.adapter = SavePoopPager(requireFragmentManager())
    bindings.viewPager.currentItem = 2
    //sort of want to do this with data binding if i can
    bindings.tabLayout.setupWithViewPager(bindings.viewPager)
    return bindings.root
  }

}
