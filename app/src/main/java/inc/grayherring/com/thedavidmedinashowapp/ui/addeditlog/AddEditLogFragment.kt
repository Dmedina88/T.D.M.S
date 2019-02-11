package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import inc.grayherring.com.thedavidmedinashowapp.R
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.CalendarViewModel
import inc.grayherring.com.thedavidmedinashowapp.ui.ViewModelFactory
import javax.inject.Inject

class AddEditLogFragment : BaseFragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  private lateinit var bindings: AddEditBindings

  private lateinit var viewModel: AddEditLogViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.add_edit_log_fragment, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(AddEditLogViewModel::class.java)

    bindings = AddEditBindings(view)
  }

}

class AddEditBindings(view: View) {
  val datePicker = view.findViewById<DatePicker>(R.id.date_picker)
  val poopTypeSpinner = view.findViewById<Spinner>(R.id.poop_spinner)
  val notesEditText = view.findViewById<EditText>(R.id.notes)
}