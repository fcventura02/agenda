package br.com.agenda.data.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.com.agenda.data.repository.ScheduleRepository
import br.com.agenda.data.model.Schedule
import br.com.agenda.data.user_data.UserDatabase
import kotlinx.coroutines.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ScheduleViewModel(application: Application): AndroidViewModel(application) {
    val readAllSchedule:LiveData<List<Schedule>>
    private val repository: ScheduleRepository

    init {
        val scheduleDao = UserDatabase.getDatabase(application).scheduleDao()
        repository = ScheduleRepository(scheduleDao)

        val date = LocalDate.now()
        readAllSchedule = repository.readAllDaySchedule(date.toString())
    }

    fun readAllDaySchedulle(): LiveData<List<Schedule>> {
        val date = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatted = date.format(formatter)
        var allDaySchedulle = GlobalScope.async { repository.readAllDaySchedule(formatted) }
        lateinit var daySchedulle: LiveData<List<Schedule>>
        runBlocking {
            daySchedulle = allDaySchedulle.await()
        }

        return  daySchedulle
    }

    fun addSchedule(schedule: Schedule){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSchedule(schedule)
        }
    }

}