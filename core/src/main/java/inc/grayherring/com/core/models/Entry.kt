package inc.grayherring.com.core.models

import org.threeten.bp.LocalDate

 data class Entry(
  val date: LocalDate,
  val poopType: EntryType,
  val name: String,
  val imagePath: String?,
  val notes: String,
  val id: Int = 0
)