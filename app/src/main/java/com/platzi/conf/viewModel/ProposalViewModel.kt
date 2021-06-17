package com.platzi.conf.viewModel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.platzi.conf.model.User
import com.platzi.conf.network.Callback
import com.platzi.conf.network.FirebaseStorageService
import com.platzi.conf.network.FirestoreService
import java.lang.Exception

class ProposalViewModel:ViewModel() {

    val firebaseStorageService = FirebaseStorageService()
    val firestoreService = FirestoreService()
    var uploadResponse: MutableLiveData<String> = MutableLiveData()
    var getDataResponse: MutableLiveData<User> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun onRefresh(){

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

    fun uploadProposal(uri: Uri) {
        firebaseStorageService.uploadProposal(object:Callback<String> {
            override fun onSuccess(result: String?) {

                var url = result

                firestoreService.getDataUser(object:Callback<User> {
                    override fun onSuccess(result: User?) {

                        result?.proposalUrl = url

                        firestoreService.simpanDataDiri(object:Callback<String> {
                            override fun onSuccess(result: String?) {
                                uploadResponse.value = result
                                processFinished()
                            }

                            override fun onFailed(exception: Exception) {
                                uploadResponse.value = exception.message
                                processFinished()
                            }
                        }, result!!)

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
        }, uri)
    }

    fun processFinished(){
        this.isLoading.value=true
    }
}