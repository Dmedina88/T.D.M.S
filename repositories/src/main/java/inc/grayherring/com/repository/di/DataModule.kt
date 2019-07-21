package inc.grayherring.com.repository.di

import inc.grayherring.com.repository.EntryRepository
import inc.grayherring.com.repository.EntryRepositoryImpl
import inc.grayherring.com.repository.NasaRepository
import inc.grayherring.com.repository.NasaRepositoryImpl
import inc.grayherring.com.repository.WorkoutRepository
import inc.grayherring.com.repository.WorkoutRepositoryImpl
import org.koin.dsl.module
import org.koin.experimental.builder.singleBy

val networkModule = inc.grayherring.com.network.di.networkModule
val persistenceModule = inc.grayherring.com.persistence.di.persistenceModule

val dataModule = module {
  singleBy<EntryRepository, EntryRepositoryImpl>()
  singleBy<NasaRepository, NasaRepositoryImpl>()
  singleBy<WorkoutRepository,WorkoutRepositoryImpl>()
}
