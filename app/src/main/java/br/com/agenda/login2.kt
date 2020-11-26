package br.com.agenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login2.*

class login2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)



        btm_login.setOnClickListener {
            if (checkLogin(et_login_email.text.toString(), et_login_password.text.toString())) {
                val intent = Intent(this, principal::class.java)
                startActivity(intent)
            }
        }
    }

    private fun checkLogin(login: String, password: String): Boolean{
        val email = "teste@teste.com"
        val senha = "123456"

        if (login != email || password != senha){
            return false
        }
        return true
    }
}