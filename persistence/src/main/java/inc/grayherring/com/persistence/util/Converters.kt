package inc.grayherring.com.persistence.util

import androidx.room.TypeConverter
import inc.grayherring.com.core.models.EntryType
import org.threeten.bp.LocalDate

class Converters {
  @TypeConverter
  fun localDateToDateStamp(localDate: LocalDate?): Long? = localDate?.toEpochDay()

  @TypeConverter
  fun localDateToCalendar(value: Long?): LocalDate? =
    value?.let { LocalDate.ofEpochDay(value) }

  @TypeConverter
  fun localDateToDateStamp(poopType: EntryType?): String? = poopType?.name

  @TypeConverter
  fun localDateToCalendar(poopType: String?): EntryType? = poopType?.let { EntryType.valueOf(it) }
}