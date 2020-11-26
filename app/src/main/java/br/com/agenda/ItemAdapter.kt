package br.com.agenda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list.view.*

class ItemAdapter(
    private val items: List<String>,
    private val listener: (String) -> Unit
) : RecyclerView.Adapter<VH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.text.text = item
        holder.itemView.setOnClickListener { listener(item) }
    }

    override fun getItemCount(): Int = items.count()
}

class VH(parent: View) : RecyclerView.ViewHolder(parent) {
    val text: TextView = parent.tvTitle
}