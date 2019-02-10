package inc.grayherring.com.thedavidmedinashowapp.ui.pooplist

import androidx.lifecycle.ViewModel
import inc.grayherring.com.thedavidmedinashowapp.data.PoopLogRepository
import javax.inject.Inject

class PoopListVM @Inject constructor(val poopLogRepository: PoopLogRepository) : ViewModel()