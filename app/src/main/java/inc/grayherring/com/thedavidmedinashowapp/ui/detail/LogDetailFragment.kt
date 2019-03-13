package inc.grayherring.com.thedavidmedinashowapp.ui.detail

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
import com.bumptech.glide.Glide
import inc.grayherring.com.thedavidmedinashowapp.R
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.data.models.icon
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentDetailsBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.ViewModelFactory
import inc.grayherring.com.thedavidmedinashowapp.util.loadImageFromPath
import inc.grayherring.com.thedavidmedinashowapp.util.map
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

class LogDetailFragment : BaseFragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  lateinit var bindings: FragmentDetailsBinding

  private val viewModel by lazy {
    ViewModelProviders.of(this, viewModelFactory)
      .get(LogDetailViewModel::class.java)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    bindings = FragmentDetailsBinding.inflate(inflater, container, false)

    viewModel.init(LogDetailFragmentArgs.fromBundle(arguments!!).id)
    bindings.run {
      viewModel.logLiveData.observe(viewLifecycleOwner, Observer {
          it?.let { data ->
            logImage.loadImageFromPath(data.imagePath ?: "")
            Glide.with(logTypeIcon.context)
              .load(data.poopType.icon)
              .circleCrop()
              .centerInside()
              .into(logTypeIcon)
            name.text = data.name
            notes.text = data.notes
            date.text = data.date.format(DateTimeFormatter.ISO_DATE)
          }
        }
        )
    }
    setHasOptionsMenu(true)

    return bindings.root
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.detail, menu)
    super.onCreateOptionsMenu(menu, inflater)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    viewModel.logLiveData.map { it.id }.observe(viewLifecycleOwner, Observer {
    if (item.itemId == R.id.action_edit) {
        val action = LogDetailFragmentDirections.actionLogDetailFragmentToPoopFlowFragment(it)
        findNavController().navigate(action)
      }
    })
    return super.onOptionsItemSelected(item)
  }

}
