package br.com.agenda.data.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.agenda.data.model.User
import br.com.agenda.data.user_data.UserDatabase
import br.com.agenda.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application)  {

    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
    }

    suspend fun loginUser(email: String): User {
         val readOneUser = GlobalScope.async{repository.getOneUser(email)}
        return readOneUser.await()
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
}