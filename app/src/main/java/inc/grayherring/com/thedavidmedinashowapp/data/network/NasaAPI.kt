package inc.grayherring.com.thedavidmedinashowapp.data.network

import inc.grayherring.com.thedavidmedinashowapp.data.models.NasaPlanetary
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaAPI {
  @GET("planetary/apod")
  fun getNasaPlanetary(@Query("date") date: String): Deferred<NasaPlanetary>
}