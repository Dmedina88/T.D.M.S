package inc.grayherring.com.thedavidmedinashowapp.data.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import inc.grayherring.com.thedavidmedinashowapp.data.models.PoopLog

private const val GET_POOP_QUERY = "SELECT * FROM poop_log WHERE id = :id LIMIT 1"

@Dao
interface PoopLogDao {
  @Query("SELECT * from poop_log ORDER BY date")
  fun getAllPoops(): LiveData<List<PoopLog>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(poopLog: PoopLog)

  @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(poopLog: PoopLog)

  @Query("DELETE FROM poop_log")
   fun deleteAll()

  @Delete
   fun deletePoopLog(poopLog: PoopLog)

  @Query("SELECT * FROM poop_log WHERE date BETWEEN :dayst AND :dayet")
  fun getFromTable(dayst: Long, dayet: Long): LiveData<List<PoopLog>>

  @Query(GET_POOP_QUERY)
  fun getPoop(id: Int): PoopLog

  @Query(GET_POOP_QUERY)
  fun getPoopLiveData(id: Int): LiveData<PoopLog>
}