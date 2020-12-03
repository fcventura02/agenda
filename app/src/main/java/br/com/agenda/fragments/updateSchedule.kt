package br.com.agenda.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.icu.util.LocaleData
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.agenda.R
import br.com.agenda.data.model.Schedule
import br.com.agenda.data.viewModel.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_update_schedule.*
import kotlinx.android.synthetic.main.fragment_update_schedule.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class updateSchedule : Fragment() {

    private  val args by navArgs<updateScheduleArgs>()
    private lateinit var mScheduleViewModel: ScheduleViewModel

     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_schedule, container, false)

         mScheduleViewModel = ViewModelProvider(this).get(ScheduleViewModel::class.java)

         view.et_schedule_update_name.setText(args.currentSchedule.name)
         view.et_schedule_update_task.setText(args.currentSchedule.task)
         view.et_schedule_update_amount.setText(args.currentSchedule.cost.toString())
         view.et_schedule_update_date.setText(args.currentSchedule.date.replace("-","/"))
         view.et_schedule_update_hour.setText(args.currentSchedule.timer)

         view.img_update_back.setOnClickListener {
             findNavController().navigate(R.id.action_updateSchedule_to_index)
         }

         view.btm_schedule_update_confirm.setOnClickListener{
             updateItem()
         }

         setHasOptionsMenu(true)

         return  view
    }

    private  fun updateItem(){
        val scheduleId = args.currentSchedule.id
        val userId = args.currentSchedule.user_id
        val name = et_schedule_update_name.text.toString()
        val task = et_schedule_update_task.text.toString()
        val amount = et_schedule_update_amount.text.toString()
        val hour = et_schedule_update_hour.text.toString()
        val dateView = et_schedule_update_date.text.toString().toString().replace("/","-")
        val date = LocalDate.parse(dateView)
        val complited = isCompleted.isChecked

        if (inputCheck(name, task, amount,date.toString(), hour)){
            val updateSchedule = Schedule(scheduleId, name, task, amount.toFloat(), date.toString(), hour, complited, userId)
            mScheduleViewModel.updateschedule(updateSchedule)
            Toast.makeText(requireContext(), "Atulizado com sucesso!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateSchedule_to_index)
        }else{
            Toast.makeText(requireContext(), "Preencha todos os campos.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name:String, task:String, amount:String, date: String, hour:String):Boolean{
        return!(TextUtils.isEmpty(name) && TextUtils.isEmpty(task) && TextUtils.isEmpty(amount)&& TextUtils.isEmpty(date)&& TextUtils.isEmpty(hour))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteSchedule()
        }

        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("RestrictedApi")
    private fun deleteSchedule(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("SIM"){_,_->
            mScheduleViewModel.deleteOneSchedule(args.currentSchedule)
            Toast.makeText(requireContext(), "Agndamento de ${args.currentSchedule.name} deletado com sucesso!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateSchedule_to_index)
        }
        builder.setNegativeButton("NÃO"){_,_ ->

        }
            .setTitle("Deletar agendamento de ${args.currentSchedule.name}?")
        builder.setMessage("Você tem certeza que deseja remover o agendamento de ${args.currentSchedule.name}?")
        builder.create().show()
    }

/*
    fun formateDate(date: String):LocalDate{
        val oldFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val newFormatter = DateTimeFormatter.ofPattern("dd-M-yyyy")
        val oldData = LocalDate.parse(date,oldFormatter)
        val day = oldData.dayOfMonth
        val month = oldData.monthValue
        val year = oldData.year
        val newData = LocalDate.parse("$day-$month-$year", newFormatter)
        return newData
    }
*/
}