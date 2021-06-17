package com.platzi.conf.viewModel

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.platzi.conf.model.User
import com.platzi.conf.network.Callback
import com.platzi.conf.network.FirebaseStorageService
import com.platzi.conf.network.LabelingService
import com.platzi.conf.network.FirestoreService
import java.lang.Exception

class DataDiriViewModel:ViewModel() {

    val firestoreService = FirestoreService()
    val labelingService = LabelingService()
    val firebaseStorageService = FirebaseStorageService()
    var uploadDataResponse: MutableLiveData<String> = MutableLiveData()
    var uploadKtpResponse: MutableLiveData<String> = MutableLiveData()
    var uploadKtmResponse: MutableLiveData<String> = MutableLiveData()
    var analisisKtmResponse: MutableLiveData<String> = MutableLiveData()
    var getDataResponse: MutableLiveData<User> = MutableLiveData()
    var analisisKtpResponse: MutableLiveData<String> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun onRefresh(){

    }

    fun analisisFotoKtm(image: Bitmap) {
        labelingService.analisisFoto(object:Callback<String> {
            override fun onSuccess(result: String?) {
                analisisKtmResponse.value = result
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                analisisKtmResponse.value = exception.message
                processFinished()
            }
        }, image)
    }

    fun analisisFotoKtp(image: Bitmap) {
        labelingService.analisisFoto(object:Callback<String> {
            override fun onSuccess(result: String?) {
                analisisKtpResponse.value = result
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                analisisKtpResponse.value = exception.message
                processFinished()
            }
        }, image)
    }

    fun getDataDiri() {
        firestoreService.getDataUser(object:Callback<User> {
            override fun onSuccess(result: User?) {
                getDataResponse.value = result
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    fun simpanDataDiri(user: User) {

        firestoreService.getDataUser(object:Callback<User> {
            override fun onSuccess(result: User?) {

                result?.namaLengkap = user.namaLengkap
                result?.noHp = user.noHp
                result?.namaBank = user.namaBank
                result?.noRekening = user.noRekening

                firestoreService.simpanDataDiri(object:Callback<String> {
                    override fun onSuccess(result: String?) {
                        uploadDataResponse.value = result
                        processFinished()
                    }

                    override fun onFailed(exception: Exception) {
                        uploadDataResponse.value = exception.message
                        processFinished()
                    }
                }, result!!)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                uploadDataResponse.value = exception.message
                processFinished()
            }
        })
    }

    fun uploadKtm(uri: Uri) {
        firebaseStorageService.uploadKtm(object:Callback<String> {
            override fun onSuccess(result: String?) {

                var url = result

                firestoreService.getDataUser(object:Callback<User> {
                    override fun onSuccess(result: User?) {

                        result?.ktmUrl = url

                        firestoreService.simpanDataDiri(object:Callback<String> {
                            override fun onSuccess(result: String?) {
                                uploadKtmResponse.value = result
                                processFinished()
                            }

                            override fun onFailed(exception: Exception) {
                                uploadKtmResponse.value = exception.message
                                processFinished()
                            }
                        }, result!!)

                        processFinished()
                    }

                    override fun onFailed(exception: Exception) {
                        uploadKtmResponse.value = exception.message
                        processFinished()
                    }
                })

                processFinished()
            }

            override fun onFailed(exception: Exception) {
                uploadKtmResponse.value = exception.message
                processFinished()
            }
        }, uri)
    }

    fun uploadKtp(uri: Uri) {
        firebaseStorageService.uploadKtp(object:Callback<String> {
            override fun onSuccess(result: String?) {

                var url = result

                firestoreService.getDataUser(object:Callback<User> {
                    override fun onSuccess(result: User?) {

                        result?.ktpUrl = url

                        firestoreService.simpanDataDiri(object:Callback<String> {
                            override fun onSuccess(result: String?) {
                                uploadKtpResponse.value = result
                                processFinished()
                            }

                            override fun onFailed(exception: Exception) {
                                uploadKtpResponse.value = exception.message
                                processFinished()
                            }
                        }, result!!)

                        processFinished()
                    }

                    override fun onFailed(exception: Exception) {
                        uploadKtpResponse.value = exception.message
                        processFinished()
                    }
                })

                processFinished()
            }

            override fun onFailed(exception: Exception) {
                uploadKtpResponse.value = exception.message
                processFinished()
            }
        }, uri)
    }


    fun processFinished(){
        this.isLoading.value=true
    }
}