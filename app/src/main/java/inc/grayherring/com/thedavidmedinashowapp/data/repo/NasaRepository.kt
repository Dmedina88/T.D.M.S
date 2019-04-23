package inc.grayherring.com.thedavidmedinashowapp.data.repo

import inc.grayherring.com.thedavidmedinashowapp.data.models.NasaPlanetary
import inc.grayherring.com.thedavidmedinashowapp.data.network.NasaAPI
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

interface NasaRepository {
  suspend fun getNasaPlanetary(date: LocalDate): NasaPlanetary
}

class NasaRepositoryImpl(private val nasaApi: NasaAPI) :
  NasaRepository {
  override suspend fun getNasaPlanetary(date: LocalDate) =
    nasaApi.getNasaPlanetary(date.format(DateTimeFormatter.ISO_DATE))
}