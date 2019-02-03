package inc.grayherring.com.thedavidmedinashowapp.ui

import androidx.lifecycle.ViewModel
import inc.grayherring.com.thedavidmedinashowapp.data.PoopLogRepository
import javax.inject.Inject

class CalendarViewModel @Inject constructor(val poopLogRepository: PoopLogRepository) : ViewModel()