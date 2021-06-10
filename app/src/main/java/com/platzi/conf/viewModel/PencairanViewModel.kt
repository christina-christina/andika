package com.platzi.conf.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.platzi.conf.model.Pencairan
import com.platzi.conf.model.Pinjaman
import com.platzi.conf.model.User
import com.platzi.conf.network.Callback
import com.platzi.conf.network.FirestoreService
import java.lang.Exception

class PencairanViewModel : ViewModel() {

    val firestoreService = FirestoreService()
    var uploadResponse: MutableLiveData<String> = MutableLiveData()
    var dataPencairanResponse: MutableLiveData<List<Pencairan>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun onRefresh() {

    }

    fun simpanDataPencairan(pencairan: Pencairan) {

        firestoreService.getDataUser(object : Callback<User> {
            override fun onSuccess(result: User?) {
                if (result != null) {

                    var saldoLama = 0
                    if (result.jummlahSaldo != null) {
                        saldoLama = result.jummlahSaldo!!.toInt()
                    }
                    var dicairkan = 0
                    if (result.dicairkan != null) {
                        dicairkan = result.dicairkan!!.toInt()
                    }

                    var nominalPencairan = pencairan.nominal!!.toInt()

                    if (nominalPencairan <= saldoLama) {

                        result.jummlahSaldo = (saldoLama - nominalPencairan).toString()
                        result.dicairkan = (dicairkan + nominalPencairan).toString()

                        firestoreService.simpanDataDiri(object : Callback<String> {
                            override fun onSuccess(result: String?) {

                                firestoreService.simpanDataPencairan(object : Callback<String> {
                                    override fun onSuccess(result: String?) {
                                        uploadResponse.value = result
                                        processFinished()
                                    }

                                    override fun onFailed(exception: Exception) {
                                        uploadResponse.value = exception.message
                                        processFinished()
                                    }
                                }, pencairan)
                            }

                            override fun onFailed(exception: Exception) {
                                uploadResponse.value = exception.message
                                processFinished()
                            }
                        }, result)
                    }
                    processFinished()
                }
            }

            override fun onFailed(exception: Exception) {
                uploadResponse.value = exception.message
                processFinished()
            }
        })

        processFinished()
    }

    fun getDataPencairan() {
        firestoreService.getDataPencairan(object : Callback<List<Pencairan>> {
            override fun onSuccess(result: List<Pencairan>?) {
                dataPencairanResponse.value = result
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    fun processFinished() {
        this.isLoading.value = true
    }
}