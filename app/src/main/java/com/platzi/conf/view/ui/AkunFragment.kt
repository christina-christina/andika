package com.platzi.conf.view.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.platzi.conf.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_akun.*
import kotlinx.android.synthetic.main.fragment_beranda.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.tv_daftar
import kotlinx.android.synthetic.main.fragment_welcome.*

/**
 * A simple [Fragment] subclass.
 */
class AkunFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_akun, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.bnvMenu?.visibility = View.VISIBLE

        super.onViewCreated(view, savedInstanceState)
        tv_cairkan_saldo.setOnClickListener{
            findNavController().navigate(R.id.action_akunFragment_to_cairkanSaldoFragment)
        }
        tv_up_proposal.setOnClickListener{
            findNavController().navigate(R.id.action_akunFragment_to_proposalFragment)
        }
        tv_data_diri.setOnClickListener{
            findNavController().navigate(R.id.action_akunFragment_to_dataDiriFragment)
        }
    }
}
