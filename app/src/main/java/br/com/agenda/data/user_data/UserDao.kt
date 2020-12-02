package br.com.agenda.data.user_data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query ("SELECT * FROM user_data as u WHERE u.email = :email")
    suspend fun readOneDataUser(email: String):User
}