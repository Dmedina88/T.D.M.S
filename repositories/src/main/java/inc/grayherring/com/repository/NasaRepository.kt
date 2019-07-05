package inc.grayherring.com.repository

import inc.grayherring.com.core.models.NasaPlanetary
import inc.grayherring.com.network.NasaAPI
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

interface NasaRepository {
  suspend fun getNasaPlanetary(date: LocalDate): NasaPlanetary
}

internal class NasaRepositoryImpl(private val nasaApi: NasaAPI) :
  NasaRepository {
  override suspend fun getNasaPlanetary(date: LocalDate) =
    nasaApi.getNasaPlanetary(date.format(DateTimeFormatter.ISO_DATE))
}