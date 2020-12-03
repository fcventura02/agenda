package br.com.agenda.data.schedule_data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.agenda.R
import br.com.agenda.data.model.Schedule
import br.com.agenda.fragments.indexDirections
import br.com.agenda.fragments.updateScheduleDirections
import kotlinx.android.synthetic.main.item_list.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ListAdapter: RecyclerView.Adapter<ListAdapter.VH>() {
    private var scheduleList = emptyList<Schedule>()

    class VH (itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))
    }

    override fun getItemCount(): Int {
        return  scheduleList.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val currentItem = scheduleList[position]
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(currentItem.date, formatter)


        holder.itemView.tvTitle.text = currentItem.name
        holder.itemView.tvAtividade.text = currentItem.task
        holder.itemView.tvDD.text = date.dayOfMonth.toString()
        holder.itemView.tvMM.text = date.month.toString()
        holder.itemView.tvValue.text =  "R$" + currentItem.cost

        holder.itemView.item_list.setOnClickListener {
            val action = indexDirections.actionIndexToUpdateSchedule(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(schedule: List<Schedule>){
        this.scheduleList = schedule
        notifyDataSetChanged()
    }
}