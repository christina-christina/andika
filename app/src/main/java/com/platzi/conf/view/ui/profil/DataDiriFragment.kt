package com.platzi.conf.view.ui.profil


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

import com.platzi.conf.R
import com.platzi.conf.model.User
import com.platzi.conf.viewModel.DataDiriViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_data_diri.*

/**
 * A simple [Fragment] subclass.
 */
class DataDiriFragment : Fragment() {

    private lateinit var viewModel: DataDiriViewModel
    private val KTM = 66
    private val KTP = 88
    private lateinit var uri: Uri

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_diri, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DataDiriViewModel::class.java)
        viewModel.onRefresh()

        activity?.bnvMenu?.visibility = View.GONE

        btn_simpan.setOnClickListener {
            var user = User(
                et_nama_lengkap.text.toString(),
                et_no_hp.text.toString(),
                et_nama_bank.text.toString(),
                et_no_rekening.text.toString()
            )
            viewModel.simpanDataDiri(user)
        }

        btn_upload_ktm.setOnClickListener {
            launchFilePickerKtm()
        }

        btn_upload_ktp.setOnClickListener {
            launchFilePickerKtp()
        }

        observerViewModel()
    }

    fun observerViewModel(){

        viewModel.uploadDataResponse.observe(viewLifecycleOwner, Observer<String> { resp ->
            Toast.makeText(activity, resp, Toast.LENGTH_SHORT).show()
            if (resp.contains("Berhasil")) {

            }
        })

        viewModel.uploadKtpResponse.observe(viewLifecycleOwner, Observer<String> { resp ->
            Toast.makeText(activity, resp, Toast.LENGTH_SHORT).show()
            if (resp.contains("Berhasil")) {
                tv_upload_ktp.setText("Sudah di-upload")
            }
        })

        viewModel.uploadKtmResponse.observe(viewLifecycleOwner, Observer<String> { resp ->
            Toast.makeText(activity, resp, Toast.LENGTH_SHORT).show()
            if (resp.contains("Berhasil")) {
                tv_upload_ktm.setText("Sudah di-upload")
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer <Boolean> {

        })
    }


    private fun launchFilePickerKtp() {
        val intent = Intent()
        intent.setType("*/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), KTP)
    }

    private fun launchFilePickerKtm() {
        val intent = Intent()
        intent.setType("*/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), KTM)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            } else {
                uri = data!!.data

                if (requestCode == KTM) {
                    viewModel.uploadKtm(uri)
                } else {
                    viewModel.uploadKtp(uri)
                }
            }
        }
    }



}
