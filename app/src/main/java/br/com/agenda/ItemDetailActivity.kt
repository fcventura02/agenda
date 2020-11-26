package br.com.agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_item_detail.*

class ItemDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        val bundle: Bundle? = intent.extras

        if (bundle != null){
            textView4.text = bundle.getString(ITEM_ID)
        }
    }

    companion object{
        const val  ITEM_ID = "my item"
    }
}