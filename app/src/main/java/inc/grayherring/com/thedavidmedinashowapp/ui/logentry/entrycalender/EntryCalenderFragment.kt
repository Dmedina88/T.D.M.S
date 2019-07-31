package inc.grayherring.com.thedavidmedinashowapp.ui.logentry.entrycalender

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentCalenderBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.calendar.CalenderAdapter
import inc.grayherring.com.thedavidmedinashowapp.ui.calendar.configureForCalender
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.threeten.bp.LocalDate

class EntryCalenderFragment : BaseFragment() {

  private val viewModel by viewModel<EntryCalenderVM>()
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
        val directions =
          EntryCalenderFragmentDirections.actionEntryCalenderFragmentToMyDialog(epochDay)
        findNavController().navigate(directions)
      }
    }

    bindings.calenderRecycler.configureForCalender(this.requireContext(), calenderAdapter)
    viewModel.calenderIteamLiveData.observe(this.viewLifecycleOwner) {
      calenderAdapter.setData(it)
    }

    return bindings.root
  }

}

