package inc.grayherring.com.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import inc.grayherring.com.persistence.models.DBEntry

private const val GET_POOP_QUERY = "SELECT * FROM poop_log WHERE id = :id LIMIT 1"

@Dao
interface EntryDao {
  @Query("SELECT * from poop_log ORDER BY date")
  fun getAllEntries(): LiveData<List<DBEntry>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(entry: DBEntry)

  @Update(onConflict = OnConflictStrategy.REPLACE)
  suspend fun update(entry: DBEntry)

  @Query("DELETE FROM poop_log")
  suspend fun deleteAll()

  @Delete
  suspend fun deleteEntryLog(entry: DBEntry)

  @Query("SELECT * FROM poop_log WHERE date BETWEEN :dayst AND :dayet")
  fun getFromTable(dayst: Long, dayet: Long): LiveData<List<DBEntry>>

  @Query(GET_POOP_QUERY)
  suspend fun getEntry(id: Int): DBEntry

  @Query(GET_POOP_QUERY)
  fun getEntryLiveData(id: Int): LiveData<DBEntry>
}