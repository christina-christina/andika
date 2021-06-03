package com.platzi.conf.view.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.platzi.conf.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_beranda.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.tv_daftar
import kotlinx.android.synthetic.main.fragment_welcome.*

/**
 * A simple [Fragment] subclass.
 */
class BerandaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beranda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.bnvMenu?.visibility = View.VISIBLE
        super.onViewCreated(view, savedInstanceState)

        btn_uang_kuliah_button.setOnClickListener{
            findNavController().navigate(R.id.action_berandaFragment_to_peminjamanUkFragment)
        }

        btn_uang_bulanan_button.setOnClickListener{
            findNavController().navigate(R.id.action_berandaFragment_to_peminjamanUbFragment)
        }

        tv_apa_isa.setOnClickListener {
            findNavController().navigate(R.id.action_berandaFragment_to_pengertianIsaFragment)
        }

        tv_cara_kerja.setOnClickListener {
            findNavController().navigate(R.id.action_berandaFragment_to_kerjaIsaFragment)
        }

        tv_cara_bayar.setOnClickListener {
            findNavController().navigate(R.id.action_berandaFragment_to_bayarIsaFragment)
        }
    }
}
