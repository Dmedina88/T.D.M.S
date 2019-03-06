package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentTakePhotoBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.ViewModelFactory
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.PoopFlowViewModel
import javax.inject.Inject

class PhotoFragment : BaseFragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  lateinit var bindings: FragmentTakePhotoBinding

  val REQUEST_IMAGE_CAPTURE = 1

  private val viewModel by lazy {
    ViewModelProviders.of(this, viewModelFactory)
      .get(PoopFlowViewModel::class.java)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    bindings = FragmentTakePhotoBinding.inflate(inflater, container, false)

    bindings.takePhotoBtn.setOnClickListener {
      dispatchTakePictureIntent()
    }
    viewModel.imagePath.observe(viewLifecycleOwner, Observer {
      if (it.isNullOrBlank()) {

      } else {

      }
    })

    return bindings.root
  }

  private fun dispatchTakePictureIntent() {
    Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
      takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
      }
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
      val imageBitmap = data?.extras?.get("data") as Bitmap
      bindings.poopImage.setImageBitmap(imageBitmap)
    }
  }
}
