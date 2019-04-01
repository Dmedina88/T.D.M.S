package inc.grayherring.com.thedavidmedinashowapp.ui.calendar

import androidx.lifecycle.ViewModel
import inc.grayherring.com.thedavidmedinashowapp.data.repo.EntryRepository
import javax.inject.Inject

class CalendarViewModel @Inject constructor(val entryRepository: EntryRepository) : ViewModel()