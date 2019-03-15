package inc.grayherring.com.thedavidmedinashowapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.transition.ChangeBounds
import androidx.transition.Transition
import androidx.transition.TransitionListenerAdapter
import androidx.transition.TransitionManager
import com.bumptech.glide.Glide
import inc.grayherring.com.thedavidmedinashowapp.R
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.data.models.icon
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentDetailsBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.ViewModelFactory
import inc.grayherring.com.thedavidmedinashowapp.ui.detail.AnimationState.FULL_DETAIL
import inc.grayherring.com.thedavidmedinashowapp.ui.detail.AnimationState.IMAGE_FULLSCREEN
import inc.grayherring.com.thedavidmedinashowapp.ui.detail.AnimationState.NONE
import inc.grayherring.com.thedavidmedinashowapp.util.map
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

class LogDetailFragment : BaseFragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  lateinit var bindings: FragmentDetailsBinding

  var isFirstLoad = false

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
    setHasOptionsMenu(true)
    bindings.run {

      viewModel.logDetailState.observe(viewLifecycleOwner, Observer {

        it?.let { state ->
          Glide.with(logTypeIcon.context)
            .load(state.log.poopType.icon)
            .circleCrop()
            .centerInside()
            .into(logTypeIcon)
          name.text = state.log.name
          notes.text = state.log.notes
          date.text = state.log.date.format(DateTimeFormatter.ISO_DATE)

          if (state.animationState == NONE) {
            Glide.with(logImage.context)
              .load(state.log.imagePath ?: "")
              .centerCrop()
              .into(logImage)
          }
          if (state.animationState == IMAGE_FULLSCREEN) {
            logImage.isEnabled = false
            transitionView(R.layout.fragment_details_image) {
              Glide.with(logImage.context)
                .load(state.log.imagePath ?: "")
                .fitCenter()
                .into(logImage)
              logImage.isEnabled = true
            }
          } else if (it.animationState == FULL_DETAIL) {
            logImage.isEnabled = false

            transitionView(R.layout.fragment_details_alt) {
              Glide.with(logImage.context)
                .load(state.log.imagePath ?: "")
                .centerCrop()
                .into(logImage)
              logImage.isEnabled = true

            }
          }
        }
      })

      logImage.setOnClickListener {
        viewModel.toggleImage()
      }



      return bindings.root
    }
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

  private fun transitionView(@LayoutRes constraintLayout: Int, onTransitionEnd: () -> Unit) {

    val constraintSet = ConstraintSet()
    constraintSet.clone(this.requireContext(), constraintLayout)

    val transition = ChangeBounds()
    transition.interpolator = AnticipateOvershootInterpolator(1.0f)
    transition.duration = 1000
    transition.addListener(object : TransitionListenerAdapter() {
      override fun onTransitionEnd(transition: Transition) {
        super.onTransitionEnd(transition)
        onTransitionEnd()
      }
    })
    TransitionManager.beginDelayedTransition(bindings.constraintLayout, transition)
    constraintSet.applyTo(bindings.constraintLayout)
  }

}
