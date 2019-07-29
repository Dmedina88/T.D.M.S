package inc.grayherring.com.persistence.poop_tracker

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import inc.grayherring.com.persistence.models.PoopEntry

private const val GET_POOP_QUERY = "SELECT * FROM poop_log WHERE id = :id LIMIT 1"

@Dao
interface EntryDao {
  @Query("SELECT * from poop_log ORDER BY date")
  fun getAllEntries(): LiveData<List<PoopEntry>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(entry: PoopEntry)

  @Update(onConflict = OnConflictStrategy.REPLACE)
  suspend fun update(entry: PoopEntry)

  @Query("DELETE FROM poop_log")
  suspend fun deleteAll()

  @Delete
  suspend fun deleteEntryLog(entry: PoopEntry)

  @Query("SELECT * FROM poop_log WHERE date BETWEEN :epochDayStart AND :epochDayEnd")
  fun getFromTable(epochDayStart: Long, epochDayEnd: Long): LiveData<List<PoopEntry>>

  @Query(GET_POOP_QUERY)
  suspend fun getEntry(id: Int): PoopEntry

  @Query(GET_POOP_QUERY)
  fun getEntryLiveData(id: Int): LiveData<PoopEntry>
}