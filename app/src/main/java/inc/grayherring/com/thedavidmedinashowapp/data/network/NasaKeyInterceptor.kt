package inc.grayherring.com.thedavidmedinashowapp.data.network

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import java.io.IOException

class NasaKeyInterceptor : Interceptor {
  @Throws(IOException::class)
  override fun intercept(chain: Chain): Response {
    val original = chain.request()
    val originalHttpUrl = original.url()

    val url = originalHttpUrl.newBuilder()
      .addQueryParameter("api_key", "FhLrcHvsX6pXOyLQ2w2UGBBTZoxtpoLrePc1ggp5")
      .build()

    // Request customization: add request headers
    val requestBuilder = original.newBuilder()
      .url(url)

    val request = requestBuilder.build()
    return chain.proceed(request)
  }
}