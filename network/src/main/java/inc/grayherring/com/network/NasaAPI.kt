package inc.grayherring.com.network

import inc.grayherring.com.core.models.NasaPlanetary
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaAPI {
  @GET("planetary/apod")
  suspend fun getNasaPlanetary(@Query("date") date: String): NasaPlanetary
}