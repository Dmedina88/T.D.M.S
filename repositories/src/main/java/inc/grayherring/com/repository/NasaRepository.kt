package inc.grayherring.com.repository

import inc.grayherring.com.core.models.NasaPlanetary
import inc.grayherring.com.network.NasaAPI
import inc.grayherring.com.network.NasaNetwork
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

interface NasaRepository {
  suspend fun getNasaPlanetary(date: LocalDate): NasaPlanetary
}

internal class NasaRepositoryImpl(private val nasaNetwork: NasaNetwork) :
  NasaRepository {
  override suspend fun getNasaPlanetary(date: LocalDate) =
    nasaNetwork.getNasaPlanetary(date.format(DateTimeFormatter.ISO_DATE))
}