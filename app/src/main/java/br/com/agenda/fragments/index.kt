package br.com.agenda.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.agenda.R
import br.com.agenda.data.schedule_data.ListAdapter
import br.com.agenda.data.viewModel.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_index.view.*
import kotlinx.android.synthetic.main.fragment_login.view.*


@Suppress("DEPRECATION")
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

        setHasOptionsMenu(true)
        /*
        Log.e("TAG","m")
        */
        // Inflate the layout for this fragment

        return view
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_sair, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_sair){
            val myPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
            val myEditor = myPreferences.edit()
            myEditor.putBoolean("save_login", false)
            myEditor.apply()
            findNavController().navigate(R.id.action_index_to_login)
        }

        return super.onOptionsItemSelected(item)
    }

}