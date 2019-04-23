package inc.grayherring.com.thedavidmedinashowapp.data.repo

import inc.grayherring.com.thedavidmedinashowapp.data.models.NasaPlanetary
import inc.grayherring.com.thedavidmedinashowapp.data.network.NasaAPI
import kotlinx.coroutines.Deferred
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

interface NasaRepository {
  fun getNasaPlanetary(date: LocalDate): Deferred<NasaPlanetary>
}

class NasaRepositoryImpl(private val nasaApi: NasaAPI) :
  NasaRepository {
  override fun getNasaPlanetary(date: LocalDate) =
    nasaApi.getNasaPlanetary(date.format(DateTimeFormatter.ISO_DATE))
}