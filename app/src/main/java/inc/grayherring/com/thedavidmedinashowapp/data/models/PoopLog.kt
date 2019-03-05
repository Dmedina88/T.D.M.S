package inc.grayherring.com.thedavidmedinashowapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import inc.grayherring.com.thedavidmedinashowapp.data.models.PoopType
import org.threeten.bp.LocalDate

@Entity(tableName = "poop_log")
data class PoopLog(
  val date: LocalDate,
//  val time: Time,
  val poopType: PoopType,
  val name: String,
  val imagePath: String?,
  val notes: String,
  @PrimaryKey(autoGenerate = true)
  val id: Int = 0
)