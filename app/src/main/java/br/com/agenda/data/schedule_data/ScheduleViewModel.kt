package br.com.agenda.data.schedule_data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ScheduleViewModel(application: Application): AndroidViewModel(application) {
    private val readAllDaySchedulle: LiveData<List<Schedule>>
    private val repository:ScheduleRepository

    init {
        val scheduleDao = ScheduleDatabase.getDatabase(application).scheduleDao()
        val date = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD")
        val formatted = date.format(formatter)
        repository = ScheduleRepository(scheduleDao)
        readAllDaySchedulle = repository.readAllDaySchedule(date = "$formatted")
    }

    fun addSchedule(schedule: Schedule){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSchedule(schedule)
        }
    }

}