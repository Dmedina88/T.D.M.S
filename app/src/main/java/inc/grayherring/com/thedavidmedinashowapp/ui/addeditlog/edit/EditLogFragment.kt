package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
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

class EditLogFragment : BaseFragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  private lateinit var bindings: AddEditBindings

  private lateinit var viewModel: EditPoopLogViewModel

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
        .get(EditPoopLogViewModel::class.java)

    bindings = AddEditBindings(view)
    viewModel.oldPoopLog(EditLogFragmentArgs.fromBundle(arguments!!).poopId)
        .observe(this, Observer {

          bindings.notesEditText.setText(it.notes)

        })

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



