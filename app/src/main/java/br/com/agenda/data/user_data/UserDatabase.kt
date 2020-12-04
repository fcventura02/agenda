package br.com.agenda.data.user_data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.agenda.data.model.Schedule
import br.com.agenda.data.model.User
import br.com.agenda.data.schedule_data.ScheduleDao
import kotlinx.coroutines.InternalCoroutinesApi

@Database(entities = [User::class, Schedule::class], version = 3, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun scheduleDao(): ScheduleDao

    companion object{
        @Volatile
        private var INSTANCEU: UserDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context):UserDatabase{
            val tempInstanceU = INSTANCEU
            if (tempInstanceU != null ){
                return tempInstanceU
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCEU = instance
                return instance
            }
        }
    }

}