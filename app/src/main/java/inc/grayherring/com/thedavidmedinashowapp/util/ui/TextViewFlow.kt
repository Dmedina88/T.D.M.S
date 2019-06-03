package inc.grayherring.com.thedavidmedinashowapp.util.ui

import android.view.View
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import timber.log.Timber

@ExperimentalCoroutinesApi
@FlowPreview
suspend fun TextView.textChangeFlow(scope: CoroutineScope) : ReceiveChannel<String>  {
val channel : Channel<String>  = Channel()
  this@textChangeFlow.doOnTextChanged { text, _, _, _ ->
    scope.launch {
      Timber.d(text.toString())
      channel.send(text.toString())
    }
  }
  return  channel
}





@FlowPreview fun View.clicks(scope: CoroutineScope) = flow {
  this@clicks.setOnClickListener {
    scope.launch {
      this@flow.emit(Unit)
    }
  }

}