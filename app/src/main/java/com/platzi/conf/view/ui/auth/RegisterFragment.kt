package com.platzi.conf.view.ui.auth


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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_register.*

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment() {

    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.bnvMenu?.visibility = View.GONE
        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        viewModel.onRefresh()

        btn_daftar.setOnClickListener{
            var user = User(
                namaLengkap = et_nama.text.toString(),
                noHp = et_no_hp.text.toString()
            )
            viewModel.register(et_email.text.toString(), et_password.text.toString(), user)
        }

        iv_right_arrow.setOnClickListener{
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        observerViewModel()
    }

    fun observerViewModel(){
        //metodo que va revisando si hay actualizacion, si lo hay lo mandara a la pantalla
        viewModel.authResponse.observe(viewLifecycleOwner, Observer<String> { resp ->
            Toast.makeText(activity, resp, Toast.LENGTH_SHORT).show()
            if (resp.contains("Berhasil")) {
                findNavController().navigate(R.id.action_registerFragment_to_afterRegistrationFragment)
            }
        })

        //controlara cuando los datos terminen de cargar
        viewModel.isLoading.observe(viewLifecycleOwner, Observer <Boolean> {

        })
    }
}
