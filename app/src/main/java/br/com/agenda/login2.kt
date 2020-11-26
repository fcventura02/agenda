package br.com.agenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login2.*

@Suppress("DEPRECATION")
class login2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
        val intentPrincipal = Intent(this, principal::class.java)
        val myPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val myEditor = myPreferences.edit()

        //Verifica se tem a preferencia de manter-se logado
        if(myPreferences.getBoolean("save_login", false)){
            startActivity(intentPrincipal)
        }

        btm_login.setOnClickListener {
                if (checkLogin(et_login_email.text.toString(), et_login_password.text.toString())) {
                        myEditor.putBoolean("save_login", checkBox.isChecked)
                        myEditor.apply()
                    startActivity(intentPrincipal)
                }else{
                    val toast = Toast.makeText(applicationContext, "Insira email e senha válidos", Toast.LENGTH_SHORT)
                    toast.show()
                }
        }

        tv_new_account.setOnClickListener {
            val intentNewAccount = Intent(this, NewAccount::class.java)
            startActivity(intentNewAccount)

        }
    }

    private fun checkLogin(login: String, password: String): Boolean{
        val myPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        myPreferences.getBoolean("save_login", false)
        val email = myPreferences.getString("email", null).toString()
        val senha = myPreferences.getString("password", null).toString()

        if (login != email || password != senha){
            return false
        }
        return true
    }
}