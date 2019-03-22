package inc.grayherring.com.thedavidmedinashowapp.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import inc.grayherring.com.thedavidmedinashowapp.data.models.Entry
import inc.grayherring.com.thedavidmedinashowapp.data.persistence.EntryDao
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

interface EntryRepository {
  fun getAllEntries(): LiveData<List<Entry>>

  @WorkerThread
  fun insert(entry: Entry)

  @WorkerThread
  suspend fun  getEntry(id: Int): Entry

  fun deleteAll()
  fun deleteEntry(entry: Entry)
  fun getEntries(dayst: Long, dayet: Long): LiveData<List<Entry>>
  fun getEntryLiveData(id: Int): LiveData<Entry>
  fun update(entry: Entry)
}

@Singleton
class EntryRepositoryImpl @Inject constructor(private val entryDao: EntryDao) :
  EntryRepository {
  override fun getEntryLiveData(id: Int) = entryDao.getEntryLiveData(id)

  override fun getAllEntries(): LiveData<List<Entry>> = entryDao.getAllEntries()

  @WorkerThread
  override fun insert(entry: Entry) = entryDao.insert(entry)

  @WorkerThread
  override fun update(entry: Entry) = entryDao.update(entry)

  override fun deleteAll() = entryDao.deleteAll()

  override fun deleteEntry(entry: Entry) = entryDao.deleteEntryLog(entry)

  override fun getEntries(dayst: Long, dayet: Long): LiveData<List<Entry>> =
    entryDao.getFromTable(dayst, dayet)

  override suspend  fun getEntry(id: Int): Entry = entryDao.getEntry(id).also {
    Timber.d(Thread.currentThread().name)

  }

}