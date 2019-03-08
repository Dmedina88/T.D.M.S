package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentDatePickerBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.ViewModelFactory
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.PoopFlowViewModel
import inc.grayherring.com.thedavidmedinashowapp.util.ui.localDate
import org.threeten.bp.LocalDate
import javax.inject.Inject

class DatePickerFragment : BaseFragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  lateinit var bindings: FragmentDatePickerBinding

  private val viewModel by lazy {
    ViewModelProviders.of(this.requireActivity(), viewModelFactory)
      .get(PoopFlowViewModel::class.java)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    bindings = FragmentDatePickerBinding.inflate(inflater, container, false)

    //todo: 2 way binding myself out of this
    viewModel.date.observe(this, Observer {
      bindings.datePicker.init(
        it.year, it.monthValue -1, it.dayOfMonth
      ) { _, year, month, dayOfMonth ->
        val localDate = LocalDate.of(year, month, dayOfMonth)
        if (it != localDate)
          viewModel.date.value = LocalDate.of(year, month, dayOfMonth)
      }
      bindings.datePicker.localDate = it
    })

    return bindings.root
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
  }
}
