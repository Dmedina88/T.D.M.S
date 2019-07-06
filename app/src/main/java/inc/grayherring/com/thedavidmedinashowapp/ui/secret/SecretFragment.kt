package inc.grayherring.com.thedavidmedinashowapp.ui.secret

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import inc.grayherring.com.thedavidmedinashowapp.arch.BaseFragment
import inc.grayherring.com.thedavidmedinashowapp.databinding.FragmentSecretBinding

class SecretFragment : BaseFragment() {

  lateinit var bindings: FragmentSecretBinding
  val args: SecretFragmentArgs by navArgs()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    bindings = FragmentSecretBinding.inflate(inflater, container, false)
    bindings.secret.text = args.id.toString()
    return bindings.root
  }

}
