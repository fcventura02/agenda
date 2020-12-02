package br.com.agenda.data.schedule_data

import androidx.lifecycle.LiveData

class ScheduleRepository(private val scheduleDao: ScheduleDao) {
    suspend fun addSchedule( schedule: Schedule){
        scheduleDao.addScheduler(schedule)
    }

    fun readAllDaySchedule(date: String): LiveData<List<Schedule>>{
       return scheduleDao.readAllDaySchedule(date)
    }

    suspend fun readAllWeekSchedule(date:String){
        scheduleDao.readAllWeekSchedule(date)
    }
}