package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.add

import android.R.layout
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.data.models.PoopType
import inc.grayherring.com.thedavidmedinashowapp.databinding.AddEditLogFragmentBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.ViewModelFactory
import javax.inject.Inject

class AddLogFragment : BaseFragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelFactory

  private val viewModel by lazy {
    ViewModelProviders.of(this, viewModelFactory).get(AddLogViewModel::class.java)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    val bindings = AddEditLogFragmentBinding.inflate(inflater, container, false)
    bindings.poopSpinner.adapter = ArrayAdapter<PoopType>(
      bindings.root.context,
      layout.simple_list_item_1, PoopType.values()
    )

    bindings.saveButton.setOnClickListener {
      //      viewModel.save(
//        PoopLog(
//          bindings.datePicker.date,
//          bindings.poopSpinner.selectedItem as PoopType,
//          bindings.saveButton.text.toString()
//        )
//      )
      findNavController().popBackStack()
    }
    setHasOptionsMenu(true)

    return bindings.root
  }

}



