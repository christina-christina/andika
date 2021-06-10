package com.platzi.conf.viewModel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.platzi.conf.model.User
import com.platzi.conf.network.Callback
import com.platzi.conf.network.FirebaseStorageService
import com.platzi.conf.network.FirestoreService
import java.lang.Exception

class DataDiriViewModel:ViewModel() {

    val firestoreService = FirestoreService()
    val firebaseStorageService = FirebaseStorageService()
    var uploadDataResponse: MutableLiveData<String> = MutableLiveData()
    var uploadKtpResponse: MutableLiveData<String> = MutableLiveData()
    var uploadKtmResponse: MutableLiveData<String> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun onRefresh(){

    }

    fun simpanDataDiri(user: User) {
        firestoreService.simpanDataDiri(object:Callback<String> {
            override fun onSuccess(result: String?) {
                uploadDataResponse.value = result
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                uploadDataResponse.value = exception.message
                processFinished()
            }
        }, user)
    }

    fun uploadKtm(uri: Uri) {
        firebaseStorageService.uploadKtm(object:Callback<String> {
            override fun onSuccess(result: String?) {
                uploadKtmResponse.value = result
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
                uploadKtpResponse.value = result
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