package inc.grayherring.com.thedavidmedinashowapp.ui.pooplist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import inc.grayherring.com.thedavidmedinashowapp.R
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.ui.ViewModelFactory

class PoopListFragment : BaseFragment() {

  lateinit var viewModelFactory: ViewModelFactory
  private lateinit var viewModel: PoopListVM
  private lateinit var poopListBindings: PoopListBindings

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
      .get(PoopListVM::class.java)

    poopListBindings = PoopListBindings(view)
    val adapter = PoopAdapter {
      val action = PoopListFragmentDirections.listToEditLogFragment(it.id)
      findNavController().navigate(action)
    }
    poopListBindings.run {
      viewModel.poopListItems.observe(viewLifecycleOwner, Observer {
        adapter.setData(it)
      })
      recyclerView.adapter = adapter
      poopListBindings.floatingActionButton.setOnClickListener {
        findNavController().navigate(R.id.list_to_addLogFragment)
      }
    }
  }

}

class PoopListBindings(view: View) {
  val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
  val floatingActionButton: FloatingActionButton = view.findViewById(R.id.fab)

  init {
    recyclerView.layoutManager = LinearLayoutManager(view.context)
    recyclerView.addItemDecoration(DividerItemDecoration(view.context, VERTICAL))
  }
}