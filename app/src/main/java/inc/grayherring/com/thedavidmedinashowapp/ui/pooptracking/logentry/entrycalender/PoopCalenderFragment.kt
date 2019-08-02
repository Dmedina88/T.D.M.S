package inc.grayherring.com.thedavidmedinashowapp.ui.pooptracking.logentry.entrycalender

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentCalenderBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.calendar.CalenderAdapter
import inc.grayherring.com.thedavidmedinashowapp.ui.calendar.configureForCalender
import inc.grayherring.com.thedavidmedinashowapp.ui.calendar.indexOfCurrentMonth
import inc.grayherring.com.thedavidmedinashowapp.ui.pooptracking.PoopTrackerHomeFragmentDirections
import inc.grayherring.com.thedavidmedinashowapp.util.observeOnce
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.threeten.bp.LocalDate

class PoopCalenderFragment : BaseFragment() {

  private val viewModel by viewModel<PoopCalenderVM>()
  private lateinit var bindings: FragmentCalenderBinding
  private val navController by lazy { requireParentFragment().findNavController() }

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
        val directions =
          PoopTrackerHomeFragmentDirections.actionPoopCalenderFragmentToListDialog(epochDay)
        navController.navigate(directions)
      }
    }

    bindings.calenderRecycler.configureForCalender(this.requireContext(), calenderAdapter)

    viewModel.calenderItemLiveData.observeOnce(this.viewLifecycleOwner, Observer {
      calenderAdapter.setData(it)
      bindings.calenderRecycler.scrollToPosition(it.indexOfCurrentMonth)
    })
    viewModel.calenderItemLiveData.observe(this.viewLifecycleOwner) {
      calenderAdapter.setData(it)
    }

    return bindings.root
  }

}

