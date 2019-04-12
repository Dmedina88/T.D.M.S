package inc.grayherring.com.thedavidmedinashowapp.di

import android.app.Application
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import inc.grayherring.com.thedavidmedinashowapp.data.network.NasaAPI
import inc.grayherring.com.thedavidmedinashowapp.data.network.NasaKeyInterceptor
import inc.grayherring.com.thedavidmedinashowapp.data.persistence.EntryDatabase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

  @Provides
  @Singleton
  fun providesEntryLogDatabase(application: Application) =
    Room.databaseBuilder(
      application,
      EntryDatabase::class.java,
      "log_db"
    ).build()

  @Provides
  @Singleton
  fun providesEntryDoa(database: EntryDatabase) =
    database.entryDao()

  @Provides
  @Singleton
  fun providesRetroFit(okHttpClient: OkHttpClient) = Retrofit.Builder()
    .baseUrl("https://api.nasa.gov/")
    .client(okHttpClient)
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

  @Provides
  @Singleton
  fun providesOkHttpClient() =
    OkHttpClient.Builder()
      .addInterceptor(NasaKeyInterceptor())
      .build()

  @Provides
  @Singleton
  fun providesNasaApi(retrofit: Retrofit) =
    retrofit.create(NasaAPI::class.java)


}
