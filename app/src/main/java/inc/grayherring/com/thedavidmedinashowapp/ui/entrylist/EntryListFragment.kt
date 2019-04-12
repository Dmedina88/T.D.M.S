package inc.grayherring.com.thedavidmedinashowapp.ui.entrylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import inc.grayherring.com.thedavidmedinashowapp.R
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentEntryListBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.ViewModelFactory
import java.util.PriorityQueue
import javax.inject.Inject
import kotlin.math.absoluteValue

class EntryListFragment : BaseFragment() {

  @Inject lateinit var viewModelFactory: ViewModelFactory
  private val viewModel by lazy {
    ViewModelProviders.of(this, viewModelFactory).get(EntryListVM::class.java)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    val bindings = FragmentEntryListBinding.inflate(inflater, container, false)

    val adapter = EntryAdapter({
      val action = EntryListFragmentDirections.actionPoopListFragmentToLogDetailFragment(it.id)
      findNavController().navigate(action)
    }, { viewModel.dateClicked(it) })

    viewModel.entryItems.observe(viewLifecycleOwner) { adapter.submitList(it) }

    mapOf<Int,Int>().toa().map { it.second }
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

  fun compareTriplets(a: Array<Int>, b: Array<Int>): Array<Int> {

    val map = mutableMapOf("a" to 0, "b" to 0)

    for(i in 0..3){
      if(a[i]> b[i]){
        map["a"] = map.getOrDefault("a",0) +1
      }
      if(a[i] < b[i]){
        map["b"] = map["b"]?.plus(1)
        1.absoluteValue
      }

      emptyArray<Int>().copyOfRange()
      val queue = PriorityQueue<Int>()
      val queue2 = PriorityQueue<Int>(Comparator { val1, val2 -> val2.compareTo(val1)})

    }
    return map.values.toTypedArray()


  }
}
