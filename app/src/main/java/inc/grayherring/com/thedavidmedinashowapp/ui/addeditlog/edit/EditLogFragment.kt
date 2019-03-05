package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.edit

import android.R.layout
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import inc.grayherring.com.thedavidmedinashowapp.R
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.data.models.PoopType
import inc.grayherring.com.thedavidmedinashowapp.databinding.AddEditLogFragmentBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.ViewModelFactory
import javax.inject.Inject

class EditLogFragment : BaseFragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  lateinit var bindings: AddEditLogFragmentBinding
  private val viewModel by lazy {
    ViewModelProviders.of(this, viewModelFactory)
      .get(EditPoopLogViewModel::class.java)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    bindings = AddEditLogFragmentBinding.inflate(inflater, container, false)
    viewModel.init(EditLogFragmentArgs.fromBundle(arguments!!).poopId)

    bindings.poopSpinner.adapter = ArrayAdapter<PoopType>(
      bindings.root.context,
      layout.simple_list_item_1, PoopType.values()
    )
    viewModel.oldPoopLog.observe(this, Observer {
      bindings.poopSpinner.setSelection(PoopType.values().indexOf(it.poopType))
      bindings.notesEditText.setText(it.notes)
    })

    bindings.notesEditText
    bindings.saveButton.setOnClickListener {
      //      viewModel.save(
//        PoopLog(
//          bindings.datePicker.date,
//          bindings.poopSpinner.selectedItem as PoopType,
//          bindings.notesEditText.text.toString()
//        )
//      )
      findNavController().popBackStack()
    }
    setHasOptionsMenu(true)

    return bindings.root
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.save, menu)
    super.onCreateOptionsMenu(menu, inflater)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
//      R.id.action_save -> {
//        viewModel.save(
//            PoopLog(
//                bindings.datePicker.date,
//                bindings.poopSpinner.selectedItem as PoopType,
//                bindings.notesEditText.text.toString()
//            )
//        )
//        findNavController().popBackStack()
//      }

    }
    return super.onOptionsItemSelected(item)
  }

}



