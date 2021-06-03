package com.platzi.conf.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.platzi.conf.model.Conference
import com.platzi.conf.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScheduleAdapter(val scheduleListener:ScheduleListener): RecyclerView.Adapter<ScheduleAdapter.ViewHolder> (){

    var listConference=ArrayList<Conference>()



    //metodo para crear o decir cual va ser el diseno que se va a utilizar para la lista para cada elemento
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleAdapter.ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_schedule,parent,false))

    //cuantos elementos tenemos
    override fun getItemCount() = this.listConference.size

    //los datos que vayamos a cargar
    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        val conference = this.listConference[position] as Conference

        holder.tvConferenceName.text=conference.title
        holder.tvConferenceSpeaker.text=conference.speaker
        holder.tvConferenceTag.text=conference.tag

        val simpleDateFormat = SimpleDateFormat("HH:mm")
        val simpleDateFormatAMPM = SimpleDateFormat("a")

        val calendar = Calendar.getInstance()
        calendar.time=conference.dateTime

        val hourFormat = simpleDateFormat.format(conference.dateTime)
        holder.tvConferenceHour.text=hourFormat
        holder.tvConferenceAMPM.text=simpleDateFormatAMPM.format(conference.dateTime).toUpperCase()
        holder.itemView.setOnClickListener {
            scheduleListener.onConferenceClicked(conference,position)
        }

    }

        fun updateData(data:List<Conference>){
            this.listConference.clear()
            this.listConference.addAll(data)
            notifyDataSetChanged()
        }


    //como enlazaremos cada uno de los elementos visuales a la variables
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val tvConferenceName = itemView.findViewById<TextView>(R.id.tvScheduleConferenceName)
        val tvConferenceSpeaker = itemView.findViewById<TextView>(R.id.tvScheduleConferenceSpeaker)
        val tvConferenceHour = itemView.findViewById<TextView>(R.id.tvScheduleHour)
        val tvConferenceTag = itemView.findViewById<TextView>(R.id.tvScheduleTag)
        val tvConferenceAMPM = itemView.findViewById<TextView>(R.id.tvScheduleAM)
    }
}