package br.com.agenda.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.agenda.R
import br.com.agenda.data.schedule_data.ListAdapter
import br.com.agenda.data.viewModel.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_index.view.*


class index : Fragment() {

    private lateinit var  mScheduleViewModel: ScheduleViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_index, container, false)

        //RecyclerView
        val adapter = ListAdapter()
        val recyclerView = view.rvContent
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //schedule view model
        mScheduleViewModel = ViewModelProvider(this).get(ScheduleViewModel::class.java)
        mScheduleViewModel.readAllSchedule.observe(viewLifecycleOwner, Observer {schedule ->
            adapter.setData(schedule)
        })

        view.btm_New_schedule.setOnClickListener {
            findNavController().navigate(R.id.action_index_to_add_schedule)
        }


        // Inflate the layout for this fragment
        return view
    }
}