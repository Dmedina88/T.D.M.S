package inc.grayherring.com.thedavidmedinashowapp.di

import androidx.room.Room
import inc.grayherring.com.thedavidmedinashowapp.data.network.NasaAPI
import inc.grayherring.com.thedavidmedinashowapp.data.network.NasaKeyInterceptor
import inc.grayherring.com.thedavidmedinashowapp.data.persistence.EntryDatabase
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {

  single<EntryDatabase> {
    Room.databaseBuilder(
      androidContext(),
      EntryDatabase::class.java,
      "log_db"
    ).build()
  }

  single {
    get<EntryDatabase>().entryDao()
  }

  single {
    OkHttpClient.Builder()
      .addInterceptor(NasaKeyInterceptor())
      .build()
  }

  single {
    Retrofit.Builder()
      .baseUrl("https://api.nasa.gov/")
      .client(get())
      .addConverterFactory(MoshiConverterFactory.create())
      .build()
  }

  single {
    get<Retrofit>().create(NasaAPI::class.java)
  }
}