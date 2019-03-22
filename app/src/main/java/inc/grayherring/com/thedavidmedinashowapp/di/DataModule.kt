package inc.grayherring.com.thedavidmedinashowapp.di

import dagger.Binds
import dagger.Module
import inc.grayherring.com.thedavidmedinashowapp.data.EntryRepository
import inc.grayherring.com.thedavidmedinashowapp.data.EntryRepositoryImpl

@Module
abstract class DataModule {

  @Binds
  abstract fun provides(poopLogRepository: EntryRepositoryImpl): EntryRepository
}