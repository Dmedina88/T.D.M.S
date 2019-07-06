package inc.grayherring.com.persistence

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import inc.grayherring.com.core.models.Entry
import inc.grayherring.com.persistence.models.DBEntry

interface EntryDatastore  {
  fun getAllEntries(): LiveData<List<DBEntry>>

  @WorkerThread
  suspend fun insert(entry: DBEntry)

  @WorkerThread
  suspend fun getEntry(id: Int): DBEntry

  suspend fun deleteAll()
  suspend fun deleteEntry(entry: DBEntry)
  suspend fun getEntries(dayst: Long, dayet: Long): LiveData<List<DBEntry>>
  suspend fun getEntryLiveData(id: Int): LiveData<DBEntry>
  suspend fun update(entry: DBEntry)
}

internal class EntryDatastoreImpl(private val entryDao: EntryDao) :
  EntryDatastore {
  override suspend fun getEntryLiveData(id: Int) =
    entryDao.getEntryLiveData(id)

  override fun getAllEntries(): LiveData<List<DBEntry>> =
    entryDao.getAllEntries()

  @WorkerThread
  override suspend fun insert(entry: DBEntry) = entryDao.insert(entry)

  @WorkerThread
  override suspend fun update(entry: DBEntry) = entryDao.update(entry)

  override suspend fun deleteAll() = entryDao.deleteAll()

  override suspend fun deleteEntry(entry: DBEntry) = entryDao.deleteEntryLog(entry)

  override suspend fun getEntries(dayst: Long, dayet: Long): LiveData<List<DBEntry>> =
    entryDao.getFromTable(dayst, dayet)

  override suspend fun getEntry(id: Int): DBEntry = entryDao.getEntry(id)
}