package com.platzi.conf.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.platzi.conf.model.Speaker
import com.platzi.conf.network.Callback
import com.platzi.conf.network.FirestoreService
import java.lang.Exception

class SpeakerViewModel:ViewModel() {
    val firestoreService = FirestoreService()
    var listSpeakers:MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()



    fun onRefresh(){
        this.getSpeakersFromFirebase()
    }

    fun getSpeakersFromFirebase(){
        this.firestoreService.getSpeakers(object:Callback<List<Speaker>> {
            override fun onSuccess(result: List<Speaker>?) {
                listSpeakers.postValue(result)
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