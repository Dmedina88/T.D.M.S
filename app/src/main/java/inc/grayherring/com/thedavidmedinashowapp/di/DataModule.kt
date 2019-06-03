package inc.grayherring.com.thedavidmedinashowapp.di

import inc.grayherring.com.thedavidmedinashowapp.data.repo.EntryRepository
import inc.grayherring.com.thedavidmedinashowapp.data.repo.EntryRepositoryImpl
import inc.grayherring.com.thedavidmedinashowapp.data.repo.NasaRepository
import inc.grayherring.com.thedavidmedinashowapp.data.repo.NasaRepositoryImpl
import org.koin.dsl.module
import org.koin.experimental.builder.singleBy

val dataModule = module {

  singleBy<EntryRepository,EntryRepositoryImpl>()
  singleBy<NasaRepository,NasaRepositoryImpl>()
}