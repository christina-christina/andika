package com.platzi.conf.view.ui

import com.google.firebase.firestore.FirebaseFirestore
import com.platzi.conf.R

data class RiwayatPeminjaman(
    val imgId : Int,
    val jenis : String,
    val nominal : String,
    val tanggal : String,
    val bulan : String
)

fun getSampleRiwayat() : ArrayList<RiwayatPeminjaman> {



    return arrayListOf(

        RiwayatPeminjaman(
            R.drawable.ic_give_money_blue,
            "Uang Bulanan",
            "Rp1.000.000",
            "30",
            "Des"
        ),
        RiwayatPeminjaman(
            R.drawable.ic_college_graduation_blue,
            "Uang Kuliah Sems 4",
            "Rp4.000.000",
            "4",
            "Aug"
        )
    )
}