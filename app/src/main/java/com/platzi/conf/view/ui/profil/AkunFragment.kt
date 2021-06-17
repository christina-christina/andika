package com.platzi.conf.view.ui.profil


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.platzi.conf.R
import com.platzi.conf.model.User
import com.platzi.conf.viewModel.AuthViewModel
import com.platzi.conf.viewModel.BerandaViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_akun.*
import java.text.NumberFormat

/**
 * A simple [Fragment] subclass.
 */
class AkunFragment : Fragment() {

    private lateinit var viewModel: BerandaViewModel

    private lateinit var viewModelLogout : AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_akun, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.bnvMenu?.visibility = View.VISIBLE

        viewModelLogout = ViewModelProvider(this).get(AuthViewModel::class.java)

        viewModel = ViewModelProvider(this).get(BerandaViewModel::class.java)
        viewModel.getDataUser()

        super.onViewCreated(view, savedInstanceState)

        tv_up_proposal.setOnClickListener{
            findNavController().navigate(R.id.action_akunFragment_to_proposalFragment)
        }
        tv_data_diri.setOnClickListener{
            findNavController().navigate(R.id.action_akunFragment_to_dataDiriFragment)
        }
        tv_keluar.setOnClickListener {
            viewModelLogout.logout()
            findNavController().navigate(R.id.action_akunFragment_to_loginFragment)
        }
        observerViewModel()
    }

    fun observerViewModel() {

        viewModel.dataResponse.observe(viewLifecycleOwner, Observer<User> { resp ->

            if (resp != null) {
                if (resp.namaLengkap != null) {
                    tv_nama.setText(resp.namaLengkap)
                } else {
                    tv_nama.setText("")
                }

                if (resp.jummlahSaldo != null) {
                    tv_saldo_nominal.setText("Rp " + NumberFormat.getInstance().format(resp.jummlahSaldo!!.toInt()))
                } else {
                    tv_saldo_nominal.setText("Rp 0")
                }

                if (resp.tunggakan != null) {
                    tv_tunggakan_nominal.setText("Rp " + NumberFormat.getInstance().format(resp.tunggakan!!.toInt()))
                } else {
                    tv_tunggakan_nominal.setText("Rp 0")
                }

                if (resp.dicairkan != null) {
                    tv_dicairkan_nominal.setText("Rp " + NumberFormat.getInstance().format(resp.dicairkan!!.toInt()))
                } else {
                    tv_dicairkan_nominal.setText("Rp 0")
                }

            } else {
                Toast.makeText(activity, "Gagal Memuat", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
