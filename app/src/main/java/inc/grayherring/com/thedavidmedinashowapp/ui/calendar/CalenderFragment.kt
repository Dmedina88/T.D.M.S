package inc.grayherring.com.thedavidmedinashowapp.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import inc.grayherring.com.thedavidmedinashowapp.R
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.ViewModelFactory
import javax.inject.Inject

class CalenderFragment : BaseFragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  val viewModel by lazy {
    ViewModelProviders.of(this, viewModelFactory).get(CalendarViewModel::class.java)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_calender, container, false)

  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel.poopLogRepository.deleteAll()
  }

}
