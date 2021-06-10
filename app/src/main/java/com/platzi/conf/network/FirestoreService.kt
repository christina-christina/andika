package com.platzi.conf.network

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.platzi.conf.model.*
import java.lang.Exception

class FirestoreService {
    val firebaseFirestore = FirebaseFirestore.getInstance()
    var userId = FirebaseAuth.getInstance().currentUser?.uid
    val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    init {
        this.firebaseFirestore.firestoreSettings = settings
    }

    fun simpanDataDiri(callback:Callback<String>, user: User) {
        userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            Log.i("id User", userId.toString())
            this.firebaseFirestore.collection("user").document(userId!!).set(user)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        callback.onSuccess("Berhasil")
                    } else if (task.exception != null) {
                        callback.onFailed(task.exception!!)
                    } else {
                        callback.onFailed(Exception("Gagal!"))
                    }
                }
        }
    }

    fun simpanDataPinjamanUb(callback:Callback<String>, pinjaman: Pinjaman) {
        if (userId != null) {

            this.firebaseFirestore.collection("pinjaman").document(userId!!).collection("UangBulanan").add(pinjaman)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        callback.onSuccess("Berhasil")
                    } else if (task.exception != null) {
                        callback.onFailed(task.exception!!)
                    } else {
                        callback.onFailed(Exception("Gagal!"))
                    }
                }
        }
    }

    fun simpanDataPinjamanUk(callback:Callback<String>, pinjaman: Pinjaman) {
        if (userId != null) {

            this.firebaseFirestore.collection("pinjaman").document(userId!!).collection("UangKuliah").add(pinjaman)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        callback.onSuccess("Berhasil")
                    } else if (task.exception != null) {
                        callback.onFailed(task.exception!!)
                    } else {
                        callback.onFailed(Exception("Gagal!"))
                    }
                }
        }
    }

    fun simpanDataPencairan(callback:Callback<String>, pencairan: Pencairan) {
        if (userId != null) {

            this.firebaseFirestore.collection("pencairan").document(userId!!).collection("pencairan").add(pencairan)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        callback.onSuccess("Berhasil")
                    } else if (task.exception != null) {
                        callback.onFailed(task.exception!!)
                    } else {
                        callback.onFailed(Exception("Gagal!"))
                    }
                }
        }
    }

    fun getDataUser(callback: Callback<User>){
        if (userId != null) {
            this.firebaseFirestore.collection("user").document(userId!!)
                .get()
                .addOnSuccessListener { result ->
                    callback.onSuccess(result.toObject(User::class.java))
                }
        }
    }

    fun getDataPinjamanUk(callback: Callback<List<Pinjaman>>){
        if (userId != null) {
            this.firebaseFirestore.collection("pinjaman").document(userId!!).collection("UangKuliah")
                .get()
                .addOnSuccessListener { result ->
                    for(doc in result){
                        val list = result.toObjects(Pinjaman::class.java)
                        callback.onSuccess(list)
                        break
                    }
                }
        }
    }

    fun getDataPinjamanUb(callback: Callback<List<Pinjaman>>){
        if (userId != null) {
            this.firebaseFirestore.collection("pinjaman").document(userId!!).collection("UangBulanan")
                .get()
                .addOnSuccessListener { result ->
                    for(doc in result){
                        val list = result.toObjects(Pinjaman::class.java)
                        callback.onSuccess(list)
                        break
                    }
                }
        }
    }

    fun getDataPembayaran(callback: Callback<List<Pembayaran>>){
        if (userId != null) {
            this.firebaseFirestore.collection("pembayaran").document(userId!!).collection("pembayaran")
                .get()
                .addOnSuccessListener { result ->
                    for(doc in result){
                        val list = result.toObjects(Pembayaran::class.java)
                        callback.onSuccess(list)
                        break
                    }
                }
        }
    }

    fun getDataPencairan(callback: Callback<List<Pencairan>>){
        if (userId != null) {
            this.firebaseFirestore.collection("pencairan").document(userId!!).collection("pencairan")
                .get()
                .addOnSuccessListener { result ->
                    for(doc in result){
                        val list = result.toObjects(Pencairan::class.java)
                        callback.onSuccess(list)
                        break
                    }
                }
        }
    }

    fun simpanDataPembayaran(callback:Callback<String>, pembayaran: Pembayaran) {
        if (userId != null) {
            this.firebaseFirestore.collection("pembayaran").document(userId!!).collection("pembayaran").add(pembayaran)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        callback.onSuccess("Berhasil")
                    } else if (task.exception != null) {
                        callback.onFailed(task.exception!!)
                    } else {
                        callback.onFailed(Exception("Gagal!"))
                    }
                }
        }
    }

}