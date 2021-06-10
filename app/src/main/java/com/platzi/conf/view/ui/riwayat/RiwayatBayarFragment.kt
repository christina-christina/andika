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
import com.platzi.conf.model.Pembayaran
import com.platzi.conf.model.Pinjaman
import com.platzi.conf.view.adapter.RiwayatPembayaranAdapter
import com.platzi.conf.viewModel.PembayaranViewModel
import com.platzi.conf.viewModel.PeminjamanViewModel
import kotlinx.android.synthetic.main.fragment_riwayat_pinjam.*


/**
 * A simple [Fragment] subclass.
 */
class RiwayatBayarFragment : Fragment() {
    val pembayaranList: MutableList<Pembayaran> = mutableListOf()

    private lateinit var viewModel: PembayaranViewModel
    private lateinit var rvAdapter: RiwayatPembayaranAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        pembayaranList.clear()
        return inflater.inflate(R.layout.fragment_riwayat_bayar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(PembayaranViewModel::class.java)
        viewModel.onRefresh()

        recyclerView.layoutManager = LinearLayoutManager(context)
        rvAdapter = RiwayatPembayaranAdapter(pembayaranList)
        recyclerView.adapter = rvAdapter

        viewModel.getDataPembayaran()

        observerViewModel()
    }

    fun observerViewModel() {

        viewModel.dataPembayaranResponse.observe(
            viewLifecycleOwner,
            Observer<List<Pembayaran>> { resp ->

                if (resp != null) {
                    resp.forEach { pembayaranList.add(it) }
                    pembayaranList.sortBy { it.tanggal }
                    rvAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(activity, "Gagal Memuat", Toast.LENGTH_SHORT).show()
                }
            })
    }

}
