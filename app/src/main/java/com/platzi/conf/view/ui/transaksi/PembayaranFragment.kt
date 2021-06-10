package com.platzi.conf.view.ui.transaksi


import android.app.Activity
import android.content.Intent
import android.net.Uri
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
import com.platzi.conf.model.Pembayaran
import com.platzi.conf.model.Pencairan
import com.platzi.conf.viewModel.PembayaranViewModel
import com.platzi.conf.viewModel.PencairanViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_cairkan_saldo.*
import kotlinx.android.synthetic.main.fragment_cairkan_saldo.et_nama_bank
import kotlinx.android.synthetic.main.fragment_data_diri.*
import kotlinx.android.synthetic.main.fragment_pembayaran.*
import kotlinx.android.synthetic.main.fragment_welcome.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class PembayaranFragment : Fragment() {

    private lateinit var viewModel: PembayaranViewModel
    private val FILE = 90
    private lateinit var uri: Uri

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pembayaran, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.bnvMenu?.visibility = View.VISIBLE

        viewModel = ViewModelProvider(this).get(PembayaranViewModel::class.java)
        viewModel.onRefresh()

        btn_kirim.setOnClickListener {
            var pembayaran = Pembayaran(
                nominal = et_nominal_bayar.text.toString(),
                tanggal = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
            )
            viewModel.simpanDataBayar(pembayaran)
        }

        btn_bukti.setOnClickListener {
            launchFilePicker()
        }

        observerViewModel()
    }

    fun observerViewModel() {

        viewModel.uploadBuktiResponse.observe(viewLifecycleOwner, Observer<String> { resp ->
            Toast.makeText(activity, resp, Toast.LENGTH_SHORT).show()
            if (resp.contains("Berhasil")) {
                tv_bukti.setText("Telah di-upload")
            }
        })

        viewModel.uploadDataResponse.observe(viewLifecycleOwner, Observer<String> { resp ->
            Toast.makeText(activity, resp, Toast.LENGTH_SHORT).show()
            if (resp.contains("Berhasil")) {
                findNavController().navigate(R.id.action_transaksiFragment_to_berandaFragment)
            }
        })
    }

    private fun launchFilePicker() {
        val intent = Intent()
        intent.setType("*/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent, "Select File"), FILE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FILE && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            } else {
                uri = data!!.data
                viewModel.uploadBuktiBayar(uri)
            }
        }
    }

}
