package com.platzi.conf.view.ui.schedule


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment

import com.platzi.conf.R
import com.platzi.conf.model.Conference
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import java.text.SimpleDateFormat

/**
 * A simple [Fragment] subclass.
 */
class ScheduleDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.FullScreenDialogStyle)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //ponemos un icono en la barrra
        tbConference.navigationIcon=ContextCompat.getDrawable(view.context,R.drawable.ic_close_white)
        //le ponemos un color de texto
        tbConference.setTitleTextColor(Color.WHITE)
        //Ponemos la accion que cuando se pique el icono se cierre el fragment
        tbConference.setNavigationOnClickListener({
            dismiss()
        })

        //obtenemos el elemento que pasamos por el bundle y lo casteamos a tipo conferencia
        val conference = arguments?.getSerializable("conference") as Conference

        tbConference.title=conference.speaker

        tvScheduleTitleConference.text=conference.title

        val pattern = "dd/MM/yyyy hh:mm a"
        val simpleDateFormar = SimpleDateFormat(pattern)
        val date = simpleDateFormar.format(conference.dateTime)
        tvDetailConferenceHour.text = date
        tvDetailConferenceSpeaker.text=conference.speaker
        tvDetailConferenceTag.text=conference.tag
        tvDetailConferenceDescription.text=conference.description
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
    }


}
