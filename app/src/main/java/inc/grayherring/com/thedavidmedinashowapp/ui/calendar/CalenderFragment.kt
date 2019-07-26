package inc.grayherring.com.thedavidmedinashowapp.ui.calendar

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentCalenderBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.threeten.bp.LocalDate

class CalenderFragment : BaseFragment() {

  private val viewModel by viewModel<CalendarViewModel>()
  private lateinit var bindings: FragmentCalenderBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    bindings = FragmentCalenderBinding.inflate(inflater, container, false)

    val calenderAdapter = CalenderAdapter {
      Toast.makeText(context, it.toString(), Toast.LENGTH_LONG).show()
    }

    bindings.calenderRecycler.configureForCalender(this.requireContext(), calenderAdapter)
    calenderAdapter.setData(
      calenderPopulated(
        LocalDate.now(),
        LocalDate.now().plusMonths(14),
        listOf(Event(LocalDate.now(), ":)"))
      )
    )
    return bindings.root
  }

}

