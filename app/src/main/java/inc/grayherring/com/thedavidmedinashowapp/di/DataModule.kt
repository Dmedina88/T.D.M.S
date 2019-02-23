package inc.grayherring.com.thedavidmedinashowapp.di

import dagger.Binds
import dagger.Module
import inc.grayherring.com.thedavidmedinashowapp.data.PoopLogRepository
import inc.grayherring.com.thedavidmedinashowapp.data.PoopLogRepositryImpl

@Module
abstract class DataModule {

  @Binds
  abstract fun provides(poopLogRepository: PoopLogRepositryImpl): PoopLogRepository
}