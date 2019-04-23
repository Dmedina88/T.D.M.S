package inc.grayherring.com.thedavidmedinashowapp.di

import inc.grayherring.com.thedavidmedinashowapp.data.repo.EntryRepository
import inc.grayherring.com.thedavidmedinashowapp.data.repo.EntryRepositoryImpl
import inc.grayherring.com.thedavidmedinashowapp.data.repo.NasaRepository
import inc.grayherring.com.thedavidmedinashowapp.data.repo.NasaRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
  single<EntryRepository> { EntryRepositoryImpl(get()) }
  single<NasaRepository> { NasaRepositoryImpl(get()) }
}