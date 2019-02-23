package inc.grayherring.com.thedavidmedinashowapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar

@Entity(tableName = "poop_log")
data class PoopLog(
    val date: Calendar,
    val poopType: PoopType,
  val notes: String,
  @PrimaryKey(autoGenerate = true)
  val id: Int = 0
)