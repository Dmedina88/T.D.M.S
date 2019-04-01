package inc.grayherring.com.thedavidmedinashowapp.data.network

import inc.grayherring.com.thedavidmedinashowapp.data.models.NasaPlanetary
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaAPI {
  @GET("planetary/apod")
  suspend fun getNasaPlanetary(@Query("date") date: String): NasaPlanetary
}