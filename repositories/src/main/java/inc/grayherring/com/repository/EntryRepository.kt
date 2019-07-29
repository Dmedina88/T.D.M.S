package inc.grayherring.com.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import inc.grayherring.com.core.models.Entry
import inc.grayherring.com.persistence.poop_tracker.EntryDatastore
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
  fun getEntries(epochDayStart: Long, epochDayEnd: Long): LiveData<List<Entry>>
  fun getEntryLiveData(id: Int): LiveData<Entry>
  suspend fun update(entry: Entry)
}

internal class EntryRepositoryImpl(private val EntryDataStore: EntryDatastore) :
  EntryRepository {
  override  fun getEntryLiveData(id: Int) =
    EntryDataStore.getEntryLiveData(id).switchMap { liveData { emit(it.toEntry()) } }

  override fun getAllEntries(): LiveData<List<Entry>> =
    EntryDataStore.getAllEntries().switchMap { liveData { emit(it.map { it.toEntry() }) } }

  @WorkerThread
  override suspend fun insert(entry: Entry) = EntryDataStore.insert(entry.toDBEntry())

  @WorkerThread
  override suspend fun update(entry: Entry) = EntryDataStore.update(entry.toDBEntry())

  override suspend fun deleteAll() = EntryDataStore.deleteAll()

  override suspend fun deleteEntry(entry: Entry) = EntryDataStore.deleteEntry(entry.toDBEntry())

  override  fun getEntries(epochDayStart: Long, epochDayEnd: Long): LiveData<List<Entry>> =
    EntryDataStore.getEntries(epochDayStart, epochDayEnd).switchMap { liveData { emit(it.map { it.toEntry() }) } }

  override suspend fun getEntry(id: Int): Entry = EntryDataStore.getEntry(id).toEntry()
}

