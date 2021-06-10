package com.platzi.conf.view.ui.transaksi


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
import com.platzi.conf.model.Pencairan
import com.platzi.conf.viewModel.PencairanViewModel
import kotlinx.android.synthetic.main.fragment_cairkan_saldo.*
import kotlinx.android.synthetic.main.fragment_cairkan_saldo.et_nama_bank
import kotlinx.android.synthetic.main.fragment_data_diri.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class CairkanSaldoFragment : Fragment() {

    private lateinit var viewModel: PencairanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cairkan_saldo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(PencairanViewModel::class.java)
        viewModel.onRefresh()

        btn_ajukan_pencairan.setOnClickListener {
            var pencairan = Pencairan(
                namaBank = et_nama_bank.text.toString(),
                nomorRekening = et_norek.text.toString(),
                nominal = et_nominal.text.toString(),
                tanggal = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
            )
            viewModel.simpanDataPencairan(pencairan)
        }
        observerViewModel()
    }

    fun observerViewModel() {

        viewModel.uploadResponse.observe(viewLifecycleOwner, Observer<String> { resp ->
            Toast.makeText(activity, resp, Toast.LENGTH_SHORT).show()
            if (resp.contains("Berhasil")) {
                findNavController().navigate(R.id.action_transaksiFragment_to_berandaFragment)
            }
        })
    }
}
