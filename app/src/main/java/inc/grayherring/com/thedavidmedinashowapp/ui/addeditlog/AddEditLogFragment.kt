package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import inc.grayherring.com.thedavidmedinashowapp.R

class AddEditLogFragment : Fragment() {

    companion object {
        fun newInstance() = AddEditLogFragment()
    }

    private lateinit var viewModel: AddEditLogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_edit_log_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddEditLogViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
