package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import inc.grayherring.com.thedavidmedinashowapp.R
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.data.PoopLog
import inc.grayherring.com.thedavidmedinashowapp.data.PoopType
import inc.grayherring.com.thedavidmedinashowapp.ui.ViewModelFactory
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.AddEditBindings
import inc.grayherring.com.thedavidmedinashowapp.util.ui.date
import javax.inject.Inject

class AddLogFragment : BaseFragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  private lateinit var bindings: AddEditBindings

  private val viewModel by lazy {
    ViewModelProviders.of(this, viewModelFactory).get(AddLogViewModel::class.java)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.add_edit_log_fragment, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    bindings = AddEditBindings(view)

    bindings.saveButton.setOnClickListener {
      viewModel.save(
          PoopLog(
              bindings.datePicker.date,
              bindings.poopTypeSpinner.selectedItem as PoopType,
              bindings.notesEditText.text.toString()
          )
      )
      findNavController().popBackStack()
    }
  }

}



