package com.platzi.conf.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.platzi.conf.model.User
import com.platzi.conf.network.Callback
import com.platzi.conf.network.FirebaseAuthService
import com.platzi.conf.network.FirestoreService
import java.lang.Exception

class AuthViewModel:ViewModel() {

    val firebaseAuth = FirebaseAuthService()
    val firestoreService = FirestoreService()
    var authResponse: MutableLiveData<String> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun onRefresh(){
    }

    fun login(email: String, password: String) {
        this.firebaseAuth.login(object:Callback<String> {
            override fun onSuccess(result: String?) {
                authResponse.value = result
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                authResponse.value = exception.message
                processFinished()
            }
        }, email, password)
    }

    fun register(email: String, password: String, user: User) {
        this.firebaseAuth.register(object:Callback<String> {
            override fun onSuccess(result: String?) {

                firestoreService.simpanDataDiri(object:Callback<String> {
                    override fun onSuccess(result: String?) {
                        authResponse.value = result
                        processFinished()
                    }

                    override fun onFailed(exception: Exception) {
                        authResponse.value = exception.message
                        processFinished()
                    }
                }, user)

                processFinished()
            }

            override fun onFailed(exception: Exception) {
                authResponse.value = exception.message
                processFinished()
            }
        }, email, password, user)
    }

    fun logout(){
        this.firebaseAuth.logout(object:Callback<String> {
            override fun onSuccess(result: String?) {
                authResponse.value = result
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                authResponse.value = exception.message
                processFinished()
            }
        })
    }

    fun processFinished(){
        this.isLoading.value=true
    }
}