package inc.grayherring.com.repository.util

import inc.grayherring.com.core.models.Entry
import inc.grayherring.com.persistence.models.PoopEntry

fun PoopEntry.toEntry() = Entry(
  date = date,
  poopType = poopType,
  name = name,
  imagePath = imagePath,
  notes = notes,
  id = id
)

fun Entry.toDBEntry() = PoopEntry(
  date = date,
  poopType = poopType,
  name = name,
  imagePath = imagePath,
  notes = notes,
  id = id
)