package com.platzi.conf.view.ui.speaker


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.platzi.conf.R
import com.platzi.conf.model.Speaker
import kotlinx.android.synthetic.main.fragment_speaker_detail_dialog.*

/**
 * A simple [Fragment] subclass.
 */
class SpeakerDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.FullScreenDialogStyle)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speaker_detail_dialog, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //ponemos un icono en la barrra
        tbSpeaker.navigationIcon= ContextCompat.getDrawable(view.context,R.drawable.ic_close_white)
        //le ponemos un color de texto
        tbSpeaker.setTitleTextColor(Color.WHITE)
        //Ponemos la accion que cuando se pique el icono se cierre el fragment
        tbSpeaker.setNavigationOnClickListener({
            dismiss()
        })



        val speaker = arguments?.getSerializable("speaker") as Speaker
        tbSpeaker.title=speaker.name
        tvDetailSpeakerName.text=speaker.name
        tvDetailSpeakerJob.text=speaker.jobTitle
        tvDetailSpeakerWorkplace.text=speaker.workplace
        tvDetailSpeakerDescription.text=speaker.biography
        Glide.with(ivSpeakerPhoto.context)
            .load(speaker.image)
            .apply(RequestOptions.circleCropTransform())
            .into(ivSpeakerPhoto)
    }
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
    }


}
