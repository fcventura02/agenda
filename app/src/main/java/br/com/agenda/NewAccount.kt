package br.com.agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new_account.*

@Suppress("DEPRECATION")
class NewAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_account)
        val myPreference = PreferenceManager.getDefaultSharedPreferences(this)
        val myEditor = myPreference.edit()


        btm_new_user_confirm.setOnClickListener{
            val name = et_new_account_name.text.toString()
            val email = et_new_account_email.text.toString()
            val password = et_new_account_password.text.toString()
            var text = "Cadastro concluido com sucesso"
            if (name.length > 2){
                if(email.length > 2 && email.contains("@") && email.contains(".com")){
                    if (password.length > 5) {
                        myEditor.putString("name", name)
                        myEditor.putString("email", email)
                        myEditor.putString("password", password)
                        myEditor.apply()
                        finish()
                    }else{
                            text = "Insira uma senha com 5 caracteres ou menos de 15"
                        }
                }else{
                    text = "Insira um email valido"
                    }
            }else{
                text = "Preencha todos os campos"
            }

            val toast = Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT)
            toast.show()

        }

        img_back.setOnClickListener {
            finish()
        }
    }

    private fun newUser (){

    }
/*
    private fun connDb (){
        val myDB: SQLiteDatabase = openOrCreateDatabase ("my.db", MODE_PRIVATE, null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS user (userId INTEGER not null primary key AUTOINCREMENT, name VARCHAR(20) NOT NULL, email VARCHAR(80) not null unique, password varchar(20) not null)")
    }
 */
}