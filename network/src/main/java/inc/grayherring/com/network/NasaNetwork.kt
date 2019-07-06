package inc.grayherring.com.network

import inc.grayherring.com.core.models.NasaPlanetary
import retrofit2.http.Query

interface NasaNetwork {
  suspend fun getNasaPlanetary(@Query("date") date: String): NasaPlanetary
}

internal class NasaNetworkImpl(val nasaAPI: NasaAPI) : NasaNetwork {
  override suspend fun getNasaPlanetary(@Query("date") date: String): NasaPlanetary = nasaAPI.getNasaPlanetary(date)
}