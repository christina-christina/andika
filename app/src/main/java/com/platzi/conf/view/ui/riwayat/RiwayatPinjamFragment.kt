package com.platzi.conf.view.ui.riwayat


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.platzi.conf.R
import com.platzi.conf.model.Pinjaman
import com.platzi.conf.view.adapter.RiwayatPeminjamanAdapter
import com.platzi.conf.viewModel.PeminjamanViewModel
import kotlinx.android.synthetic.main.fragment_riwayat_pinjam.*


/**
 * A simple [Fragment] subclass.
 */
class RiwayatPinjamFragment : Fragment() {
    val pinjamanList: MutableList<Pinjaman> = mutableListOf()

    private lateinit var viewModel: PeminjamanViewModel
    private lateinit var rvAdapter: RiwayatPeminjamanAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        pinjamanList.clear()
        return inflater.inflate(R.layout.fragment_riwayat_pinjam, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(PeminjamanViewModel::class.java)
        viewModel.onRefresh()

        recyclerView.layoutManager = LinearLayoutManager(context)
        rvAdapter = RiwayatPeminjamanAdapter(pinjamanList)
        recyclerView.adapter = rvAdapter

        viewModel.getDataPinjamanUb()
        viewModel.getDataPinjamanUk()

        observerViewModel()
    }

    fun observerViewModel() {

        viewModel.dataPinjamanUbResponse.observe(viewLifecycleOwner, Observer<List<Pinjaman>> { resp ->

            if (resp != null) {
                resp.forEach { pinjamanList.add(it) }
                pinjamanList.sortBy { it.tanggalPengajuan }
                rvAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "Gagal Memuat", Toast.LENGTH_SHORT).show()
            }
        })


        viewModel.dataPinjamanUkResponse.observe(viewLifecycleOwner, Observer<List<Pinjaman>> { resp ->

            if (resp != null) {
                resp.forEach { pinjamanList.add(it) }
                pinjamanList.sortBy { it.tanggalPengajuan }
                rvAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "Gagal Memuat", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
