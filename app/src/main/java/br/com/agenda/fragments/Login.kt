@file:Suppress("DEPRECATION")

package br.com.agenda.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.agenda.R
import br.com.agenda.data.model.User
import br.com.agenda.data.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.coroutines.*

class Login : Fragment() {
    private lateinit var  mUserViewModel: UserViewModel
    @SuppressLint("CommitPrefEdits")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val myPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        //Verifica se tem a preferencia de manter-se logado
        if(myPreferences.getBoolean("save_login", false)){
            findNavController().navigate(R.id.action_login_to_index)
        }

        view.btm_login.setOnClickListener {
            login(view)
        }

        view.tv_new_account.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_new_account2)
        }

        return view
    }

    private fun login(view: View){
        val myPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val myEditor = myPreferences.edit()
        val email = view.et_login_email.text.toString()
        val password = view.et_login_password.text.toString()

        if (inputCheck(email, password)) {

            var checkedLogin = GlobalScope.async {
                    checkLogin(email, password)
            }

            runBlocking {
                if (checkedLogin.await()) {
                    Toast.makeText(requireContext(), email, Toast.LENGTH_SHORT).show()
                    myEditor.putBoolean("save_login", view.checkBox.isChecked)
                    myEditor.apply()
                    findNavController().navigate(R.id.action_login_to_index)

                } else {
                    Toast.makeText(requireContext(),
                        "Insira email e senha válidos",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            Toast.makeText(requireContext(),
                "Insira email e senha válidos para fazer o login",
                Toast.LENGTH_SHORT).show()
        }

    }

    private suspend fun checkLogin(login: String, password: String): Boolean{
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

         val user: Deferred<User> =  GlobalScope.async{ mUserViewModel.loginUser(login)  }
         val u = user.await()
         val email:String  = u.email
         val senha:String = u.password

            if (login != email || password != senha) {
                return false
            }
            return true

    }

    private fun inputCheck(email:String, password:String):Boolean{
        return!(TextUtils.isEmpty(email) && TextUtils.isEmpty(password))
    }

}