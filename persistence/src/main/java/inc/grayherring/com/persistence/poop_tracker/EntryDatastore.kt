package inc.grayherring.com.persistence.poop_tracker

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import inc.grayherring.com.persistence.models.PoopEntry

interface EntryDatastore {
  fun getAllEntries(): LiveData<List<PoopEntry>>

  @WorkerThread
  suspend fun insert(entry: PoopEntry)

  @WorkerThread
  suspend fun getEntry(id: Int): PoopEntry

  suspend fun deleteAll()
  suspend fun deleteEntry(entry: PoopEntry)
  fun getEntries(epochDayStart: Long, epochDayEnd: Long): LiveData<List<PoopEntry>>
  fun getEntryLiveData(id: Int): LiveData<PoopEntry>
  suspend fun update(entry: PoopEntry)
}

internal class EntryDatastoreImpl(private val entryDao: EntryDao) :
  EntryDatastore {
  override fun getEntryLiveData(id: Int) =
    entryDao.getEntryLiveData(id)

  override fun getAllEntries(): LiveData<List<PoopEntry>> =
    entryDao.getAllEntries()

  @WorkerThread
  override suspend fun insert(entry: PoopEntry) = entryDao.insert(entry)

  @WorkerThread
  override suspend fun update(entry: PoopEntry) = entryDao.update(entry)

  override suspend fun deleteAll() = entryDao.deleteAll()

  override suspend fun deleteEntry(entry: PoopEntry) = entryDao.deleteEntryLog(entry)

  override fun getEntries(epochDayStart: Long, epochDayEnd: Long): LiveData<List<PoopEntry>> =
    entryDao.getFromTable(epochDayStart, epochDayEnd)

  override suspend fun getEntry(id: Int): PoopEntry = entryDao.getEntry(id)
}