package br.com.agenda.data.repository

import androidx.lifecycle.LiveData
import br.com.agenda.data.schedule_data.ScheduleDao
import br.com.agenda.data.model.Schedule

class ScheduleRepository(private val scheduleDao: ScheduleDao) {
    suspend fun addSchedule( schedule: Schedule){
        scheduleDao.addScheduler(schedule)
    }

    fun readAllDaySchedule(date: String): LiveData<List<Schedule>>{
       return scheduleDao.readAllDaySchedule(date)
    }

    fun readAllWeekSchedule(date:String): LiveData<List<Schedule>>{
        return scheduleDao.readAllWeekSchedule(date)
    }

    suspend fun updateSchedule(schedule: Schedule){
        scheduleDao.updateSchedule(schedule)
    }

    suspend fun deleteOneSchedule(schedule: Schedule){
        scheduleDao.deleteOneSchedule(schedule)
    }

    suspend fun deleteAllSchedule(){
        scheduleDao.deleteAllSchedule()
    }
}