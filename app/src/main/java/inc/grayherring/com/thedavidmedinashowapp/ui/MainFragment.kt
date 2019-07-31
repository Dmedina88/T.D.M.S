package inc.grayherring.com.thedavidmedinashowapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import inc.grayherring.com.thedavidmedinashowapp.R
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentCalenderBinding
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentMainBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.calendar.CalenderAdapter
import inc.grayherring.com.thedavidmedinashowapp.ui.calendar.configureForCalender
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.threeten.bp.LocalDate

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

