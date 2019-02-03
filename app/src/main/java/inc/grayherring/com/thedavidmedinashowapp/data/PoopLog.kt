package inc.grayherring.com.thedavidmedinashowapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "poop_log")
data class PoopLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: Calendar,
    val poopType: PoopType,
    val notes: String
)