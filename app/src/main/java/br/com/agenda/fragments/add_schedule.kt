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
import br.com.agenda.data.model.Schedule
import br.com.agenda.data.viewModel.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_add_schedule.*
import kotlinx.android.synthetic.main.fragment_add_schedule.view.*
import java.lang.Exception
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class add_schedule : Fragment() {
        private lateinit var mSchedule: ScheduleViewModel
    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_schedule, container, false)
        mSchedule = ViewModelProvider(this).get(ScheduleViewModel::class.java)

        view.btm_schedule_confirm.setOnClickListener {
            addNewSchedule()
        }

        view.img_back.setOnClickListener {
            findNavController().backStack
        }

        return view
    }

    private fun addNewSchedule(){
        val name = et_new_schedule_name.text.toString()
        val task = et_new_schedule_task.text.toString()
        val amount = et_new_schedule_amount.text.toString()
        val hour = et_new_schedule_hour.text.toString()
        val dateView = et_new_schedule_date.text.toString().replace("/","-")
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val date = LocalDate.parse(dateView,formatter)

        try {
            if (inputCheck(name, task, amount,date, hour)){
                val schedule = Schedule(0,name,task, amount.toFloat(), date.toString(), hour, false, 1 )
                mSchedule.addSchedule(schedule)
                Toast.makeText(requireContext(), "Adicionado com secesso!" , Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_add_schedule_to_index)
            }
        }catch (err:Exception){
            Toast.makeText(requireContext(), "$err.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(name:String, task:String, amount:String, date: LocalDate, hour:String):Boolean{
        return!(TextUtils.isEmpty(name) && TextUtils.isEmpty(task) && TextUtils.isEmpty(amount))
    }
}