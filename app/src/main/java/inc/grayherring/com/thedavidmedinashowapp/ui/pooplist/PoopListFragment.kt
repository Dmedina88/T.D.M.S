package inc.grayherring.com.thedavidmedinashowapp.ui.pooplist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import inc.grayherring.com.thedavidmedinashowapp.R
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.CalendarViewModel
import inc.grayherring.com.thedavidmedinashowapp.ui.ViewModelFactory
import javax.inject.Inject


class PoopListFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: CalendarViewModel
    private lateinit var poopListBindings: PoopListBindings
    private var adapter = PoopAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CalendarViewModel::class.java)

        poopListBindings = PoopListBindings(view)

        poopListBindings.floatingActionButton.setOnClickListener {
            Toast.makeText(this.context, "hello", Toast.LENGTH_LONG).show()
        }

        poopListBindings.recyclerView.adapter = adapter

        viewModel.poopLogRepository.getAllPoops().observe(this, Observer {
            adapter.setData(it)
        })

        poopListBindings.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_poopListFragment_to_addEditLogFragment)
        }
    }


}

class PoopListBindings(view: View) {
    val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
    val floatingActionButton: FloatingActionButton = view.findViewById(R.id.fab)
}