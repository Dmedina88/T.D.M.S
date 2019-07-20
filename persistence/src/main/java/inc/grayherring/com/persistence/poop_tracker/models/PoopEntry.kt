package inc.grayherring.com.persistence.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import inc.grayherring.com.core.models.EntryType
import org.threeten.bp.LocalDate

@Entity(tableName = "poop_log")
 data class PoopEntry(
  val date: LocalDate,
  val poopType: EntryType,
  val name: String,
  val imagePath: String?,
  val notes: String,
  @PrimaryKey(autoGenerate = true)
  val id: Int = 0
)