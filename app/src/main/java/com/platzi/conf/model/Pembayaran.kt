package com.platzi.conf.model

data class Pembayaran (
    var pembayaranId: String? = null,
    var userId: String? = null,
    var nominal: String? = null,
    var tanggal: String? = null,
    var status: String? = null,
    var buktiUrl: String? = null
)