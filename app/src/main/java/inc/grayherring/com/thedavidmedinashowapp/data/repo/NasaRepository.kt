package inc.grayherring.com.thedavidmedinashowapp.data.repo

import inc.grayherring.com.thedavidmedinashowapp.data.models.NasaPlanetary
import inc.grayherring.com.thedavidmedinashowapp.data.network.NasaAPI
import kotlinx.coroutines.Deferred
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

interface NasaRepository {
  fun getNasaPlanetary(date: LocalDate): Deferred<NasaPlanetary>
}

@Singleton
class NasaRepositoryImpl @Inject constructor(private val nasaApi: NasaAPI) :
  NasaRepository {
  override fun getNasaPlanetary(date: LocalDate) =
    nasaApi.getNasaPlanetary(date.format(DateTimeFormatter.ISO_DATE))
}