@file:Suppress("DEPRECATION")

package br.com.agenda.fragments

import android.annotation.SuppressLint
import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_new_account.*
import kotlinx.android.synthetic.main.fragment_new_account.view.*
import java.lang.Exception


class new_account : Fragment() {

    private lateinit var  mUserViewModel: UserViewModel

    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_account, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.btm_new_user_confirm.setOnClickListener{
            addNewAccount()

        }

        view.img_back.setOnClickListener {
            findNavController().backStack
        }
        // Inflate the layout for this fragment
        return view
    }

    private fun addNewAccount(){
        val name = et_new_account_name.text.toString()
        val email =et_new_account_email.text.toString()
        val password = et_new_account_password.text.toString()

            try {
                if (inputCheck(name,email,password)){
                val user = User(0, name, email, password)
                mUserViewModel.addUser(user)
                Toast.makeText(requireContext(), "Adicionado com secesso!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_new_account_to_login)
                }else{
                    Toast.makeText(requireContext(), "Preencha todos os campos.", Toast.LENGTH_SHORT).show()
                }
            }catch (err: Exception){
                Toast.makeText(requireContext(), "Algo deu errado ao cadastrar.", Toast.LENGTH_SHORT).show()
            }
    }

    private fun inputCheck(name:String, email:String, password:String):Boolean{
        return!(TextUtils.isEmpty(name) && TextUtils.isEmpty(email) && TextUtils.isEmpty(password))
    }


}