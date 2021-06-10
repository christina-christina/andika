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
    var dataPinjamanUkResponse: MutableLiveData<List<Pinjaman>> = MutableLiveData()
    var dataPinjamanUbResponse: MutableLiveData<List<Pinjaman>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun onRefresh(){

    }

    fun simpanPinjamanUb(pinjaman: Pinjaman) {
        firestoreService.simpanDataPinjamanUb(object:Callback<String> {
            override fun onSuccess(result: String?) {
                uploadResponse.value = result

                firestoreService.getDataUser(object:Callback<User> {
                    override fun onSuccess(result: User?) {
                        if (result != null) {

                            var saldoLama = 0
                            if (result.jummlahSaldo != null) {
                                saldoLama = result.jummlahSaldo!!.toInt()
                            }

                            var tunggakan = 0
                            if (result.tunggakan != null) {
                                tunggakan = result.tunggakan!!.toInt()
                            }

                            result.jummlahSaldo = (saldoLama + pinjaman.nominalPengajuan!!.toInt()).toString()
                            result.tunggakan = (tunggakan + pinjaman.nominalPengajuan!!.toInt()).toString()

                            firestoreService.simpanDataDiri(object : Callback<String> {
                                override fun onSuccess(result: String?) {
                                    processFinished()
                                }

                                override fun onFailed(exception: Exception) {
                                    uploadResponse.value = exception.message
                                    processFinished()
                                }
                            }, result)
                        }
                        processFinished()
                    }

                    override fun onFailed(exception: Exception) {
                        uploadResponse.value = exception.message
                        processFinished()
                    }
                })

                processFinished()
            }

            override fun onFailed(exception: Exception) {
                uploadResponse.value = exception.message
                processFinished()
            }
        }, pinjaman)
    }

    fun simpanPinjamanUk(pinjaman: Pinjaman) {
        firestoreService.simpanDataPinjamanUk(object:Callback<String> {
            override fun onSuccess(result: String?) {
                uploadResponse.value = result

                firestoreService.getDataUser(object:Callback<User> {
                    override fun onSuccess(result: User?) {
                        if (result != null) {

                            var saldoLama = 0
                            if (result.jummlahSaldo != null) {
                                saldoLama = result.jummlahSaldo!!.toInt()
                            }

                            var tunggakan = 0
                            if (result.tunggakan != null) {
                                tunggakan = result.tunggakan!!.toInt()
                            }

                            result.jummlahSaldo = (saldoLama + pinjaman.nominalPengajuan!!.toInt()).toString()
                            result.tunggakan = (tunggakan + pinjaman.nominalPengajuan!!.toInt()).toString()

                            firestoreService.simpanDataDiri(object : Callback<String> {
                                override fun onSuccess(result: String?) {
                                    processFinished()
                                }

                                override fun onFailed(exception: Exception) {
                                    uploadResponse.value = exception.message
                                    processFinished()
                                }
                            }, result)
                        }
                        processFinished()
                    }

                    override fun onFailed(exception: Exception) {
                        uploadResponse.value = exception.message
                        processFinished()
                    }
                })

                processFinished()
            }

            override fun onFailed(exception: Exception) {
                uploadResponse.value = exception.message
                processFinished()
            }
        }, pinjaman)
    }

    fun getDataPinjamanUb() {
        firestoreService.getDataPinjamanUb(object:Callback<List<Pinjaman>> {
            override fun onSuccess(result: List<Pinjaman>?) {
                dataPinjamanUbResponse.value = result
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    fun getDataPinjamanUk() {
        firestoreService.getDataPinjamanUk(object:Callback<List<Pinjaman>> {
            override fun onSuccess(result: List<Pinjaman>?) {
                dataPinjamanUkResponse.value = result
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