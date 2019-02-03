package inc.grayherring.com.thedavidmedinashowapp.util

import androidx.room.TypeConverter
import inc.grayherring.com.thedavidmedinashowapp.data.PoopType
import java.util.*

class Converters {
    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar?): Long? = calendar?.timeInMillis

    @TypeConverter
    fun datestampToCalendar(value: Long?): Calendar? = value?.let { Calendar.getInstance().apply { timeInMillis = it } }


    @TypeConverter
    fun calendarToDatestamp(poopType: PoopType?): String? = poopType?.name

    @TypeConverter
    fun datestampToCalendar(poopType: String?): PoopType? = poopType?.let { PoopType.valueOf(it) }
}