package inc.grayherring.com.thedavidmedinashowapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentMainBinding

//idk if this is the main fragment or jsut a place holder need to think about this
class MainFragment : BaseFragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return FragmentMainBinding.inflate(inflater, container, false).root
  }
}

