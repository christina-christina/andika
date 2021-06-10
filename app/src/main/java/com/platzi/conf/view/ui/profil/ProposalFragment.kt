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
import com.platzi.conf.viewModel.ProposalViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_proposal.*

/**
 * A simple [Fragment] subclass.
 */
class ProposalFragment : Fragment() {

    private val PDF = 55
    private lateinit var uri: Uri
    private lateinit var viewModel: ProposalViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_proposal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProposalViewModel::class.java)
        viewModel.onRefresh()

        activity?.bnvMenu?.visibility = View.GONE

        btn_proposal.setOnClickListener {
            launchFilePicker()
        }
        observerViewModel()
    }

    fun observerViewModel(){
        //metodo que va revisando si hay actualizacion, si lo hay lo mandara a la pantalla
        viewModel.uploadResponse.observe(viewLifecycleOwner, Observer<String> { resp ->
            Toast.makeText(activity, resp, Toast.LENGTH_SHORT).show()
            if (resp.contains("Berhasil")) {
                tv_proposal.setText("Sudah di-upload")
            }
        })

        //controlara cuando los datos terminen de cargar
        viewModel.isLoading.observe(viewLifecycleOwner, Observer <Boolean> {

        })
    }

    private fun launchFilePicker() {
        val intent = Intent()
        intent.setType("*/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent, "Select PDF"), PDF)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PDF && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            } else {
                uri = data!!.data
                viewModel.uploadProposal(uri)
            }
        }
    }


}
