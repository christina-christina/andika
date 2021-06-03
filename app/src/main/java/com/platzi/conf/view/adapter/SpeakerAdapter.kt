package com.platzi.conf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.platzi.conf.R
import com.platzi.conf.model.Speaker

class SpeakerAdapter (val speakerListener: SpeakerListener):RecyclerView.Adapter<SpeakerAdapter.ViewHolder>(){
    var listSpeakers = ArrayList<Speaker>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeakerAdapter.ViewHolder = SpeakerAdapter.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_speaker,parent,false))

    override fun getItemCount(): Int = this.listSpeakers.size

    override fun onBindViewHolder(holder: SpeakerAdapter.ViewHolder, position: Int) {

        val speaker = this.listSpeakers[position] as Speaker
        holder.tvSpeakerName.text = speaker.name
        holder.tvSpeakerJob.text=speaker.workplace

        //manda el contexto de la imagen y al final donde se manda
        Glide.with(holder.itemView.context)
            .load(speaker.image)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.ivSpeakerPhoto)

        holder.itemView.setOnClickListener {
            speakerListener.onSpeakerClicked(speaker,position)
        }


    }


    fun updateData(data:List<Speaker>){
        this.listSpeakers.clear()
        this.listSpeakers.addAll(data)
        notifyDataSetChanged()
    }



    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val tvSpeakerName = itemView.findViewById<TextView>(R.id.tvSpeakerName)
        val tvSpeakerJob = itemView.findViewById<TextView>(R.id.tvSpeakerJob)
        val ivSpeakerPhoto = itemView.findViewById<ImageView>(R.id.ivSpeakerPhoto)

    }
}