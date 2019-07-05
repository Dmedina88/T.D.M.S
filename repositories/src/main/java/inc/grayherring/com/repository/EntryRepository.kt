package inc.grayherring.com.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import inc.grayherring.com.core.models.Entry
import inc.grayherring.com.persistence.EntryDao
import inc.grayherring.com.repository.util.toDBEntry
import inc.grayherring.com.repository.util.toEntry

interface EntryRepository {
  fun getAllEntries(): LiveData<List<Entry>>

  @WorkerThread
  suspend fun insert(entry: Entry)

  @WorkerThread
  suspend fun getEntry(id: Int): Entry

  suspend fun deleteAll()
  suspend fun deleteEntry(entry: Entry)
  suspend fun getEntries(dayst: Long, dayet: Long): LiveData<List<Entry>>
  suspend fun getEntryLiveData(id: Int): LiveData<Entry>
  suspend fun update(entry: Entry)
}

internal class EntryRepositoryImpl(private val entryDao: EntryDao) :
  EntryRepository {
  override suspend fun getEntryLiveData(id: Int) =
    entryDao.getEntryLiveData(id).switchMap { liveData { emit(it.toEntry()) } }

  override fun getAllEntries(): LiveData<List<Entry>> =
    entryDao.getAllEntries().switchMap { liveData { emit(it.map { it.toEntry() }) } }

  @WorkerThread
  override suspend fun insert(entry: Entry) = entryDao.insert(entry.toDBEntry())

  @WorkerThread
  override suspend fun update(entry: Entry) = entryDao.update(entry.toDBEntry())

  override suspend fun deleteAll() = entryDao.deleteAll()

  override suspend fun deleteEntry(entry: Entry) = entryDao.deleteEntryLog(entry.toDBEntry())

  override suspend fun getEntries(dayst: Long, dayet: Long): LiveData<List<Entry>> =
    entryDao.getFromTable(dayst, dayet).switchMap { liveData { emit(it.map { it.toEntry() }) } }

  override suspend fun getEntry(id: Int): Entry = entryDao.getEntry(id).toEntry()
}

