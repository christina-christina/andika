package com.platzi.conf.view.ui.location


import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment

import com.platzi.conf.R
import com.platzi.conf.model.Location
import kotlinx.android.synthetic.main.fragment_location_detail_dialog.*
import java.net.URI

/**
 * A simple [Fragment] subclass.
 */
class LocationDetailDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? { return inflater.inflate(R.layout.fragment_location_detail_dialog, container, false) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.FullScreenDialogStyle)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //ponemos un icono en la barrra
        tbLocation.navigationIcon= ContextCompat.getDrawable(view.context,R.drawable.ic_close_white)
        //le ponemos un color de texto
        tbLocation.setTitleTextColor(Color.WHITE)
        //Ponemos la accion que cuando se pique el icono se cierre el fragment
        tbLocation.setNavigationOnClickListener({
            dismiss()
        })


        val location = Location()
        tvDetailNamePlace.text=location.name
        tvDetailAddress.text=location.address
        tvDetailPhone.text=location.phone
        tvDetailWebsite.text=location.website

        tbLocation.title =location.name

                //Creamos un evento on click para que cuando le presione el usuario se abra la aplicacion externa en este caso action dial
        llPhonePlace.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${location.phone}")
            }
            startActivity(intent)
        }
        llWebsite.setOnClickListener{
            val intent=Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(location.website)
            }
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)

    }

}
