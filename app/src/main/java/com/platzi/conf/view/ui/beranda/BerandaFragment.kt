package com.platzi.conf.view.ui.beranda


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
import com.platzi.conf.viewModel.BerandaViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_beranda.*
import java.text.NumberFormat

/**
 * A simple [Fragment] subclass.
 */
class BerandaFragment : Fragment() {

    private lateinit var viewModel: BerandaViewModel

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

        viewModel = ViewModelProvider(this).get(BerandaViewModel::class.java)
        viewModel.onRefresh()
        viewModel.getDataUser()

        btn_uang_kuliah_button.setOnClickListener {
            findNavController().navigate(R.id.action_berandaFragment_to_peminjamanUkFragment)
        }

        btn_uang_bulanan_button.setOnClickListener {
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
                    tv_saldo.setText("Rp " + NumberFormat.getInstance().format(resp.jummlahSaldo!!.toInt()))
                } else {
                    tv_saldo.setText("Rp 0")
                }
            } else {
                Toast.makeText(activity, "Gagal Memuat", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
