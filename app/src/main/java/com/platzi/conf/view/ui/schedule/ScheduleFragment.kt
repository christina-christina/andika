package com.platzi.conf.view.ui.schedule


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.platzi.conf.R
import com.platzi.conf.model.Conference
import com.platzi.conf.view.adapter.ScheduleAdapter
import com.platzi.conf.view.adapter.ScheduleListener
import com.platzi.conf.viewModel.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*

/**
 * A simple [Fragment] subclass.
 */
class ScheduleFragment : Fragment(),ScheduleListener {

    private lateinit var scheduleAdapter:ScheduleAdapter
    private lateinit var viewModel:ScheduleViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Obteniendo la instancia del apadtador
        viewModel = ViewModelProvider(this).get(ScheduleViewModel::class.java)
        //ejecutando el metodo que nos trae los datos
        viewModel.onRefresh()


        scheduleAdapter = ScheduleAdapter(this)

        //asignando que el layout sera un linear layout
        rvSchedule.apply {
            layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)
            adapter=scheduleAdapter
        }
        observerViewModel()
    }
    fun observerViewModel(){
        //metodo que va revisando si hay actualizacion, si lo hay lo mandara a la pantalla
        viewModel.listSchedule.observe(this, Observer<List<Conference>> { schedule ->
            scheduleAdapter.updateData(schedule)
        })

        //controlara cuando los datos terminen de cargar
        viewModel.isLoading.observe(this, Observer <Boolean> {
            if(it != null){
                rlBaseSchedule.visibility=View.INVISIBLE
            }
        })
    }

    override fun onConferenceClicked(conference: Conference, position: Int) {
            //aqui va a mandar los datos con el nombre conference y el valor
            val bundle = bundleOf("conference" to conference)
            findNavController().navigate(R.id.scheduleDetailFragmentDialog,bundle)
    }

}
