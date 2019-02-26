package inc.grayherring.com.thedavidmedinashowapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

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

  @Query("SELECT * FROM poop_log WHERE id = :id LIMIT 1")
  fun getPoop(id: Int): LiveData<PoopLog>
}