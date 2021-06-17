package com.platzi.conf.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.platzi.conf.model.Pinjaman
import com.platzi.conf.model.User
import com.platzi.conf.network.Callback
import com.platzi.conf.network.FirestoreService
import java.lang.Exception

class PeminjamanViewModel:ViewModel() {

    val firestoreService = FirestoreService()
    var uploadResponse: MutableLiveData<String> = MutableLiveData()
    var dataPinjamanResponse: MutableLiveData<List<Pinjaman>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun onRefresh(){

    }

    fun simpanPinjaman(pinjaman: Pinjaman) {
        firestoreService.simpanDataPinjaman(object:Callback<String> {
            override fun onSuccess(result: String?) {
                uploadResponse.value = result
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                uploadResponse.value = exception.message
                processFinished()
            }
        }, pinjaman)
    }

    fun getDataPinjaman() {
        firestoreService.getDataPinjaman(object:Callback<List<Pinjaman>> {
            override fun onSuccess(result: List<Pinjaman>?) {
                dataPinjamanResponse.value = result
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    fun processFinished(){
        this.isLoading.value=true
    }
}