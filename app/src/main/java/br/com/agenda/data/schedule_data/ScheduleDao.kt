package br.com.agenda.data.schedule_data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.agenda.data.model.Schedule


@Dao
interface ScheduleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addScheduler(schedule: Schedule)

    @Query("SELECT * FROM schedule_data as s ")
    //WHERE DATE(s.date) = DATE(:date)
    fun readAllDaySchedule(): LiveData<List<Schedule>>

    @Query("SELECT * FROM schedule_data as s WHERE s.date BETWEEN DATE(:date) and DATE(:date, '+7 day')")
    fun readAllWeekSchedule(date:String): LiveData<List<Schedule>>
}