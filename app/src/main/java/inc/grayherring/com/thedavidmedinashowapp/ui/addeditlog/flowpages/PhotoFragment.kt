package inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.flowpages

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.lifecycle.observe
import inc.grayherring.com.thedavidmedinashowapp.BuildConfig
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentTakePhotoBinding
import inc.grayherring.com.thedavidmedinashowapp.ui.addeditlog.EntryFlowViewModel
import inc.grayherring.com.thedavidmedinashowapp.util.loadImageFromPath
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import timber.log.Timber
import java.io.File
import java.io.IOException

class PhotoFragment : BaseFragment() {

  lateinit var bindings: FragmentTakePhotoBinding

  private val REQUEST_CAPTURE_IMAGE = 100

  var pendingImagePath = ""

  private val viewModel by sharedViewModel<EntryFlowViewModel>(from = this::requireParentFragment)

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    bindings = FragmentTakePhotoBinding.inflate(inflater, container, false)

    bindings.takePhotoBtn.setOnClickListener {
      openCamera()
    }
    viewModel.imagePath.observe(viewLifecycleOwner) {
      if (!it.isNullOrBlank()) {
        bindings.poopImage.loadImageFromPath(it)
      }
    }

    return bindings.root
  }

  private fun openCamera() {
    Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
      // Ensure that there's a camera activity to handle the intent
      takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
        // Create the File where the photo should go
        val photoFile: File? = try {
          createImageFile()

        } catch (ex: IOException) {
          // Error occurred while creating the File
          null
        }

        // Continue only if the File was successfully created
        photoFile?.also {
          val fileUri = FileProvider.getUriForFile(
            this.requireContext(),
            "${BuildConfig.APPLICATION_ID}.provider",
            it
          )
          pendingImagePath = it.absolutePath
          Timber.d(pendingImagePath)
          takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
          startActivityForResult(takePictureIntent, REQUEST_CAPTURE_IMAGE)
        }
      }
    }
  }

  //move some place
  @Throws(IOException::class)
  private fun createImageFile(): File {
    val timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    val imageFileName = "IMG_" + timeStamp + "_"
    val storageDir = this.requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile(
      imageFileName,
      ".jpg",
      storageDir
    )
  }

  override fun onActivityResult(
    requestCode: Int, resultCode: Int, data: Intent?
  ) {
    if (requestCode == REQUEST_CAPTURE_IMAGE && resultCode == RESULT_OK) {
      viewModel.imagePath.value = pendingImagePath
    }
  }
}


