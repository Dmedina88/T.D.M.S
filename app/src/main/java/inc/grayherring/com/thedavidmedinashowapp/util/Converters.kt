package inc.grayherring.com.thedavidmedinashowapp.util

import androidx.room.TypeConverter
import inc.grayherring.com.thedavidmedinashowapp.data.PoopType
import org.threeten.bp.LocalDate

class Converters {
  @TypeConverter
  fun localDateToDateStamp(localDate: LocalDate?): Long? = localDate?.toEpochDay()

  @TypeConverter
  fun localDateToCalendar(value: Long?): LocalDate? =
    value?.let { LocalDate.ofEpochDay(value) }

  @TypeConverter
  fun localDateToDateStamp(poopType: PoopType?): String? = poopType?.name

  @TypeConverter
  fun localDateToCalendar(poopType: String?): PoopType? = poopType?.let { PoopType.valueOf(it) }
}