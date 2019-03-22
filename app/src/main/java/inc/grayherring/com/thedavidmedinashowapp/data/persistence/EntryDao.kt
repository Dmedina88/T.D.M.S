package inc.grayherring.com.thedavidmedinashowapp.data.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import inc.grayherring.com.thedavidmedinashowapp.data.models.Entry

private const val GET_POOP_QUERY = "SELECT * FROM poop_log WHERE id = :id LIMIT 1"

@Dao
interface EntryDao {
  @Query("SELECT * from poop_log ORDER BY date")
  fun getAllEntries(): LiveData<List<Entry>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entry: Entry)

  @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(entry: Entry)

  @Query("DELETE FROM poop_log")
   fun deleteAll()

  @Delete
   fun deleteEntryLog(entry: Entry)

  @Query("SELECT * FROM poop_log WHERE date BETWEEN :dayst AND :dayet")
  fun getFromTable(dayst: Long, dayet: Long): LiveData<List<Entry>>

  @Query(GET_POOP_QUERY)
  fun getEntry(id: Int): Entry

  @Query(GET_POOP_QUERY)
  fun getEntryLiveData(id: Int): LiveData<Entry>
}