package com.platzi.conf.viewModel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.platzi.conf.model.Pembayaran
import com.platzi.conf.model.Pinjaman
import com.platzi.conf.model.User
import com.platzi.conf.network.Callback
import com.platzi.conf.network.FirebaseStorageService
import com.platzi.conf.network.FirestoreService
import java.lang.Exception

class PembayaranViewModel:ViewModel() {

    val firebaseStorageService = FirebaseStorageService()
    val firestoreService = FirestoreService()
    var uploadBuktiResponse: MutableLiveData<String> = MutableLiveData()
    var uploadDataResponse: MutableLiveData<String> = MutableLiveData()
    var dataPembayaranResponse: MutableLiveData<List<Pembayaran>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun onRefresh(){

    }

    fun uploadBuktiBayar(uri: Uri) {
        firebaseStorageService.uploadBuktiPembayaran(object:Callback<String> {
            override fun onSuccess(result: String?) {
                uploadBuktiResponse.value = result
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                uploadBuktiResponse.value = exception.message
                processFinished()
            }
        }, uri)
    }

    fun simpanDataBayar(pembayaran: Pembayaran) {

        firestoreService.getDataUser(object : Callback<User> {
            override fun onSuccess(result: User?) {
                if (result != null) {

                    var tunggakan = 0
                    if (result.tunggakan != null) {
                        tunggakan = result.tunggakan!!.toInt()
                    }

                    var nominalPembayaran = pembayaran.nominal!!.toInt()

                    if (nominalPembayaran <= tunggakan) {

                        result.tunggakan = (tunggakan - nominalPembayaran).toString()

                        firestoreService.simpanDataDiri(object : Callback<String> {
                            override fun onSuccess(result: String?) {

                                firestoreService.simpanDataPembayaran(object:Callback<String> {
                                    override fun onSuccess(result: String?) {
                                        uploadDataResponse.value = result
                                        processFinished()
                                    }

                                    override fun onFailed(exception: Exception) {
                                        uploadDataResponse.value = exception.message
                                        processFinished()
                                    }
                                }, pembayaran)
                            }

                            override fun onFailed(exception: Exception) {
                                uploadDataResponse.value = exception.message
                                processFinished()
                            }
                        }, result)
                    }
                    processFinished()
                }
            }

            override fun onFailed(exception: Exception) {
                uploadDataResponse.value = exception.message
                processFinished()
            }
        })

        processFinished()

    }

    fun getDataPembayaran() {
        firestoreService.getDataPembayaran(object:Callback<List<Pembayaran>> {
            override fun onSuccess(result: List<Pembayaran>?) {
                dataPembayaranResponse.value = result
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