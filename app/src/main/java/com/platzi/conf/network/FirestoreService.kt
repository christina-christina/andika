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

    fun simpanDataPinjaman(callback:Callback<String>, pinjaman: Pinjaman) {
        if (userId != null) {

            pinjaman.userId = userId

            this.firebaseFirestore.collection("pinjaman").add(pinjaman)
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

            pencairan.userId = userId

            this.firebaseFirestore.collection("pencairan").add(pencairan)
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
                .addOnFailureListener {
                    callback.onFailed( it )
                }
        }
    }

    fun getDataPinjaman(callback: Callback<List<Pinjaman>>){
        if (userId != null) {
            this.firebaseFirestore.collection("pinjaman")
                .get()
                .addOnSuccessListener { result ->

                    val listPinjaman = mutableListOf<Pinjaman>()

                    for(doc in result){
                        val item = doc.toObject(Pinjaman::class.java)

                        if (item.userId == userId){
                            listPinjaman.add(item)
                        }
                    }
                    callback.onSuccess(listPinjaman)
                }
        }
    }

    fun getDataPembayaran(callback: Callback<List<Pembayaran>>){
        if (userId != null) {
            this.firebaseFirestore.collection("pembayaran")
                .get()
                .addOnSuccessListener { result ->

                    val listPembayaran = mutableListOf<Pembayaran>()

                    for(doc in result){
                        val item = doc.toObject(Pembayaran::class.java)

                        if (item.userId == userId) {
                            listPembayaran.add(item)
                        }
                    }
                    callback.onSuccess(listPembayaran)
                }
        }
    }

    fun getDataPencairan(callback: Callback<List<Pencairan>>){
        if (userId != null) {
            this.firebaseFirestore.collection("pencairan")
                .get()
                .addOnSuccessListener { result ->

                    val listPencairan = mutableListOf<Pencairan>()

                    for(doc in result){
                        val item = doc.toObject(Pencairan::class.java)

                        if (item.userId == userId) {
                            listPencairan.add(item)
                        }
                    }
                    callback.onSuccess(listPencairan)
                }
        }
    }

    fun simpanDataPembayaran(callback:Callback<String>, pembayaran: Pembayaran) {
        if (userId != null) {

            pembayaran.userId = userId

            this.firebaseFirestore.collection("pembayaran").add(pembayaran)
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