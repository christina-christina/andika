package com.platzi.conf.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.platzi.conf.model.User
import com.platzi.conf.network.Callback
import com.platzi.conf.network.FirestoreService
import java.lang.Exception

class BerandaViewModel:ViewModel() {

    val firestoreService = FirestoreService()
    var dataResponse: MutableLiveData<User> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun onRefresh(){

    }

    fun getDataUser() {
        firestoreService.getDataUser(object:Callback<User> {
            override fun onSuccess(result: User?) {
                dataResponse.value = result
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