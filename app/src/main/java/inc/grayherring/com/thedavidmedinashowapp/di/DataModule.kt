package inc.grayherring.com.thedavidmedinashowapp.di

import dagger.Binds
import dagger.Module
import inc.grayherring.com.thedavidmedinashowapp.data.repo.EntryRepository
import inc.grayherring.com.thedavidmedinashowapp.data.repo.EntryRepositoryImpl
import inc.grayherring.com.thedavidmedinashowapp.data.repo.NasaRepository
import inc.grayherring.com.thedavidmedinashowapp.data.repo.NasaRepositoryImpl

@Module
abstract class DataModule {

  @Binds
  abstract fun providesEntryRepository(entryRepositoryImpl: EntryRepositoryImpl): EntryRepository

  @Binds
  abstract fun providesentryNasaRepository(nasaRepository: NasaRepositoryImpl): NasaRepository
}