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
import com.platzi.conf.model.Pencairan
import com.platzi.conf.model.Pinjaman
import com.platzi.conf.view.adapter.RiwayatPembayaranAdapter
import com.platzi.conf.view.adapter.RiwayatPencairanAdapter
import com.platzi.conf.viewModel.PeminjamanViewModel
import com.platzi.conf.viewModel.PencairanViewModel
import kotlinx.android.synthetic.main.fragment_riwayat_pinjam.*


/**
 * A simple [Fragment] subclass.
 */
class RiwayatCairFragment : Fragment() {
    val pencairanList: MutableList<Pencairan> = mutableListOf()

    private lateinit var viewModel: PencairanViewModel
    private lateinit var rvAdapter: RiwayatPencairanAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        pencairanList.clear()
        return inflater.inflate(R.layout.fragment_riwayat_cair, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(PencairanViewModel::class.java)
        viewModel.onRefresh()

        recyclerView.layoutManager = LinearLayoutManager(context)
        rvAdapter = RiwayatPencairanAdapter(pencairanList)
        recyclerView.adapter = rvAdapter

        viewModel.getDataPencairan()

        observerViewModel()
    }

    fun observerViewModel() {

        viewModel.dataPencairanResponse.observe(
            viewLifecycleOwner,
            Observer<List<Pencairan>> { resp ->

                if (resp != null) {
                    resp.forEach { pencairanList.add(it) }
                    pencairanList.sortBy { it.tanggal }
                    rvAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(activity, "Gagal Memuat", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
