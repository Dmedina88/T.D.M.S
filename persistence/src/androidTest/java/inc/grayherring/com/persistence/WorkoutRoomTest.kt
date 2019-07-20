package inc.grayherring.com.persistence;

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import inc.grayherring.com.persistence.workout_tracker.WorkoutDao
import inc.grayherring.com.persistence.workout_tracker.WorkoutDatabase
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4::class)
class WorkoutRoomTest {
  private lateinit var workoutDao: WorkoutDao
  private lateinit var db: WorkoutDatabase

  @get:Rule
  val instantTaskExecutorRule = InstantTaskExecutorRule()

  @Before
  fun createDb() {
    val instrumentationContext = InstrumentationRegistry.getInstrumentation().context
    db = Room.inMemoryDatabaseBuilder(instrumentationContext,WorkoutDatabase::class.java)

      .build()
    workoutDao = db.workoutDao()
  }
  @After
  @Throws(IOException::class)
  fun closeDb() {
    db.close()
  }

  @Test
  @Throws(Exception::class)
  fun writeUserAndReadInList() =runBlocking {
    val workout = createWorkoutWithExercises(1).first()

    workoutDao.insertWorkout(workout.workout)

  val livedate =  workoutDao.monitorWorkout(workout.workout.id)
    livedate.observeForever { Log.d("test",it.toString()) }

    assertEquals(workout.workout,livedate.value?.workout)
    assertEquals(0,livedate.value?.exerciseSets?.size)

    workoutDao.insertExerciseSet(workout.exerciseSets[0])
    assertEquals(1,livedate.value?.exerciseSets?.size)
    workoutDao.insertExerciseSet(workout.exerciseSets[1])
    assertEquals(2,livedate.value?.exerciseSets?.size)
    workoutDao.insertExerciseSet(workout.exerciseSets[2])
    assertEquals(3,livedate.value?.exerciseSets?.size)


  }
}

