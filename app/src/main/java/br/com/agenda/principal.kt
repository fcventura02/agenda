package br.com.agenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_principal.*

class principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        val list = mutableListOf<String>("item: Item")

        (1..100).forEachIndexed { index, _ -> list.add("Item $index") }

        rvContent.run {
            layoutManager = LinearLayoutManager(this@principal)
            adapter = ItemAdapter(list){
                adapterOnClick(it)
            }
        }
    }

    private fun adapterOnClick(item: String){
        val intent = Intent(this, ItemDetailActivity::class.java)
        intent.putExtra(ITEM_ID, item)
        startActivity(intent)
    }

    companion object{
        const val  ITEM_ID = "my item"
    }
}