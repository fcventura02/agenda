package br.com.agenda.data.user_data

class UserRepository(private val userDao: UserDao) {

    suspend fun getOneUser(email:String):User {
        val readOneDataUser: User = userDao.readOneDataUser(email)
        return readOneDataUser
    }

    suspend fun  addUser(user: User){
        userDao.addUser(user)
    }
}