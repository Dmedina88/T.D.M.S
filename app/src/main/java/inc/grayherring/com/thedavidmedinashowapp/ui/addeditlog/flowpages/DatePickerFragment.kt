package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentDatePickerBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.EntryFlowViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.threeten.bp.LocalDate

class DatePickerFragment : BaseFragment() {

  lateinit var bindings: FragmentDatePickerBinding

  private val viewModel by sharedViewModel<EntryFlowViewModel>(from = this::requireParentFragment)

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    bindings = FragmentDatePickerBinding.inflate(inflater, container, false)
    viewModel.date.observe(viewLifecycleOwner, Observer {
      bindings.datePicker.init(
        it.year, it.monthValue -1, it.dayOfMonth
      ) { _, year, month, dayOfMonth ->
        val localDate = LocalDate.of(year, month +1, dayOfMonth)
        if (it != localDate)
          viewModel.date.value = LocalDate.of(localDate.year,localDate.month, localDate.dayOfMonth)
      }
    })


    return bindings.root
  }

}
