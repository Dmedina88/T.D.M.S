package inc.grayherring.com.thedavidmedinashowapp.ui.calendar

import androidx.lifecycle.ViewModel
import inc.grayherring.com.thedavidmedinashowapp.data.repo.EntryRepository

class CalendarViewModel constructor(val entryRepository: EntryRepository) : ViewModel()