package br.com.agenda.data.repository

import br.com.agenda.data.model.User
import br.com.agenda.data.user_data.UserDao
import kotlinx.coroutines.*

class UserRepository(private val userDao: UserDao) {

    suspend fun getOneUser(email:String): User {
        val readOneDataUser: Deferred<User> =   GlobalScope.async{
            userDao.readOneDataUser(email) }
            return readOneDataUser.await()

    }

    suspend fun  addUser(user: User){
        userDao.addUser(user)
    }
}