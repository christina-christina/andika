package com.platzi.conf.network

import android.net.Uri
import android.widget.Toast
import androidx.core.net.toUri
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.platzi.conf.model.User
import kotlinx.android.synthetic.main.fragment_proposal.*
import java.lang.Exception

class FirebaseStorageService {

    val firebaseStorage = FirebaseStorage.getInstance()

    fun uploadProposal(callback:Callback<String>, uri: Uri) {
        firebaseStorage.getReference("proposal").child(uri.toString()).putFile(uri)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful) {
                        callback.onSuccess("Berhasil upload")
                    } else if (task.exception != null) {
                        callback.onFailed(task.exception!!)
                    } else {
                        callback.onFailed(Exception("Gagal"))
                    }
                }
    }

    fun uploadKtm(callback:Callback<String>, uri: Uri) {
        firebaseStorage.getReference("ktm").child(uri.toString()).putFile(uri)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    callback.onSuccess("Berhasil upload")
                } else if (task.exception != null) {
                    callback.onFailed(task.exception!!)
                } else {
                    callback.onFailed(Exception("Gagal"))
                }
            }
    }

    fun uploadKtp(callback:Callback<String>, uri: Uri) {
        firebaseStorage.getReference("ktp").child(uri.toString()).putFile(uri)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    callback.onSuccess("Berhasil upload")
                } else if (task.exception != null) {
                    callback.onFailed(task.exception!!)
                } else {
                    callback.onFailed(Exception("Gagal"))
                }
            }
    }

    fun uploadBuktiPembayaran(callback:Callback<String>, uri: Uri) {
        firebaseStorage.getReference("pembayaran").child(uri.toString()).putFile(uri)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    callback.onSuccess("Berhasil upload")
                } else if (task.exception != null) {
                    callback.onFailed(task.exception!!)
                } else {
                    callback.onFailed(Exception("Gagal"))
                }
            }
    }

}