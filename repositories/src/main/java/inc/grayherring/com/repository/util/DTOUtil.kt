package inc.grayherring.com.repository.util

import inc.grayherring.com.core.models.Entry
import inc.grayherring.com.persistence.models.DBEntry

fun DBEntry.toEntry() = Entry(
  date = date,
  poopType = poopType,
  name = name,
  imagePath = imagePath,
  notes = notes,
  id = id
)

fun Entry.toDBEntry() = DBEntry(
  date = date,
  poopType = poopType,
  name = name,
  imagePath = imagePath,
  notes = notes,
  id = id
)