package inc.grayherring.com.thedavidmedinashowapp.ui.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.observe
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentCalenderBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.calendar.CalenderAdapter
import inc.grayherring.com.thedavidmedinashowapp.ui.calendar.configureForCalender
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.threeten.bp.LocalDate

class WorkoutCalenderFragment : BaseFragment() {

  private val viewModel by viewModel<WorkoutCalenderVM>()
  private lateinit var bindings: FragmentCalenderBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    bindings = FragmentCalenderBinding.inflate(inflater, container, false)

    val calenderAdapter = CalenderAdapter {
      Toast.makeText(context, it.toString(), Toast.LENGTH_LONG).show()
      //todo maybe handle this but fulling in the start of every segment with the last days of last month
      if (it.month != 0) {
        val epochDay = LocalDate.of(it.year, it.month, it.day).toEpochDay()

      }
    }

    bindings.calenderRecycler.configureForCalender(this.requireContext(), calenderAdapter)
    viewModel.calenderItemLiveData.observe(this.viewLifecycleOwner) {
      calenderAdapter.setData(it)
    }

    return bindings.root
  }

}

