package inc.grayherring.com.thedavidmedinashowapp.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import inc.grayherring.com.thedavidmedinashowapp.data.persistence.PoopLogDao
import inc.grayherring.com.thedavidmedinashowapp.data.models.PoopLog
import javax.inject.Inject
import javax.inject.Singleton

interface PoopLogRepository {
  fun getAllPoops(): LiveData<List<PoopLog>>

  @WorkerThread
  fun insert(poopLog: PoopLog)

  fun deleteAll()
  fun deletePoopLog(poopLog: PoopLog)
  fun getPoops(dayst: Long, dayet: Long): LiveData<List<PoopLog>>
  fun getPoop(id: Int): LiveData<PoopLog>
  fun update(poopLog: PoopLog)
}

@Singleton
class PoopLogRepositryImpl @Inject constructor(private val poopLogDao: PoopLogDao) :
  PoopLogRepository {

  override fun getAllPoops(): LiveData<List<PoopLog>> = poopLogDao.getAllPoops()

  @WorkerThread
  override fun insert(poopLog: PoopLog) = poopLogDao.insert(poopLog)

  @WorkerThread
  override fun update(poopLog: PoopLog) = poopLogDao.update(poopLog)

  override fun deleteAll() = poopLogDao.deleteAll()

  override fun deletePoopLog(poopLog: PoopLog) = poopLogDao.deletePoopLog(poopLog)

  override fun getPoops(dayst: Long, dayet: Long): LiveData<List<PoopLog>> =
    poopLogDao.getFromTable(dayst, dayet)

  override fun getPoop(id: Int): LiveData<PoopLog> = poopLogDao.getPoop(id)

}