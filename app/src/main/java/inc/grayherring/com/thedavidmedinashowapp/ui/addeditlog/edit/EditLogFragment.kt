package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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

    setHasOptionsMenu(true)

    bindings = AddEditBindings(view)
    viewModel.init(EditLogFragmentArgs.fromBundle(arguments!!).poopId)
    viewModel.oldPoopLog.observe(this, Observer {
      bindings.poopTypeSpinner.setSelection(PoopType.values().indexOf(it.poopType))
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

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.save, menu)
    super.onCreateOptionsMenu(menu, inflater)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.action_save -> {
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
    return super.onOptionsItemSelected(item)
  }

}



