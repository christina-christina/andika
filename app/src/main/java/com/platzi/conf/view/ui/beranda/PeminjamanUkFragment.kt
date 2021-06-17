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
import com.platzi.conf.model.Pinjaman
import com.platzi.conf.viewModel.PeminjamanViewModel
import kotlinx.android.synthetic.main.fragment_peminjaman_uk.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class PeminjamanUkFragment : Fragment() {

    private lateinit var viewModel: PeminjamanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_peminjaman_uk, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(PeminjamanViewModel::class.java)
        viewModel.onRefresh()


        btn_ajukan.setOnClickListener {

            var pinjaman = Pinjaman(
                namaUniversitas = et_nama_univ.text.toString(),
                fakultas = et_fakultas.text.toString(),
                prodi = et_prodi.text.toString(),
                alamatUniversitas = et_alamat_univ.text.toString(),
                nominalPengajuan = et_biaya_semester.text.toString(),
                tanggalPengajuan = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()),
                jenis = "uang_kuliah",
                status = "Menunggu konfirmasi"
            )
            viewModel.simpanPinjaman(pinjaman)
        }
        observerViewModel()
    }

    fun observerViewModel() {

        viewModel.uploadResponse.observe(viewLifecycleOwner, Observer<String> { resp ->
            Toast.makeText(activity, resp, Toast.LENGTH_SHORT).show()
            if (resp.contains("Berhasil")) {
                findNavController().navigate(R.id.action_peminjamanUkFragment_to_berandaFragment)
            }
        })
    }

}
