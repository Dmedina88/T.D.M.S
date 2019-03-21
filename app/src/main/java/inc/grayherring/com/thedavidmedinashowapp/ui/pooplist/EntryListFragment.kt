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
import inc.grayherring.com.thedavidmedinashowapp.R
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentPoopListBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.ViewModelFactory
import javax.inject.Inject

class PoopListFragment : BaseFragment() {

  @Inject lateinit var viewModelFactory: ViewModelFactory
  private val viewModel by lazy {
    ViewModelProviders.of(this, viewModelFactory).get(PoopListVM::class.java)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    val bindings = FragmentPoopListBinding.inflate(inflater, container, false)

    val adapter = PoopAdapter({
      val action = PoopListFragmentDirections.actionPoopListFragmentToLogDetailFragment(it.id)
      findNavController().navigate(action)
    }, {})

    viewModel.poopListItems.observe(viewLifecycleOwner, Observer {
      adapter.setData(it)
    })

    bindings.run {
      recyclerView.layoutManager = LinearLayoutManager(root.context)
      recyclerView.addItemDecoration(DividerItemDecoration(root.context, VERTICAL))
      recyclerView.adapter = adapter
      fab.setOnClickListener {

        findNavController().navigate(R.id.action_list_to_flow)
      }
    }
    return bindings.root
  }

}
