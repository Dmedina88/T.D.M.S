package inc.grayherring.com.network.di


import inc.grayherring.com.network.NasaNetwork
import inc.grayherring.com.network.NasaNetworkImpl
import okhttp3.OkHttpClient
import org.koin.dsl.module
import org.koin.experimental.builder.singleBy
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {

  single {
    val okhttp = OkHttpClient.Builder()
      .addInterceptor(inc.grayherring.com.network.NasaKeyInterceptor())
      .build()
    Retrofit.Builder()
      .baseUrl("https://api.nasa.gov/")
      .client(okhttp)
      .addConverterFactory(MoshiConverterFactory.create())
      .build().create(inc.grayherring.com.network.NasaAPI::class.java)
  }

  singleBy<NasaNetwork,NasaNetworkImpl>()
}