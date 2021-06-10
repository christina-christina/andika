package com.platzi.conf.viewModel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.platzi.conf.network.Callback
import com.platzi.conf.network.FirebaseStorageService
import java.lang.Exception

class ProposalViewModel:ViewModel() {

    val firebaseStorageService = FirebaseStorageService()
    var uploadResponse: MutableLiveData<String> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun onRefresh(){

    }

    fun uploadProposal(uri: Uri) {
        firebaseStorageService.uploadProposal(object:Callback<String> {
            override fun onSuccess(result: String?) {
                uploadResponse.value = result
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                uploadResponse.value = exception.message
                processFinished()
            }
        }, uri)
    }

    fun processFinished(){
        this.isLoading.value=true
    }
}