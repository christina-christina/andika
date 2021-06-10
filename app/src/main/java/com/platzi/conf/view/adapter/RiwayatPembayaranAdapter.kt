package com.platzi.conf.view.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.platzi.conf.R
import com.platzi.conf.model.Pembayaran
import kotlinx.android.synthetic.main.riwayat_pembayaran_card.view.*
import java.time.format.DateTimeFormatter

class RiwayatPembayaranAdapter(val riwayat : MutableList<Pembayaran>) : RecyclerView.Adapter<RiwayatPembayaranAdapter.RiwayatPembayaranViewHolder>() {

    class RiwayatPembayaranViewHolder(val card : View) : RecyclerView.ViewHolder(card)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RiwayatPembayaranViewHolder, position: Int) {
        if (holder != null){
            val riwayat_item = riwayat[position]

            holder.card.tv_image.setImageResource(R.drawable.ic_give_money_blue)

            holder.card.tv_nominal.text = "Rp " + riwayat_item.nominal

            val tanggal = DateTimeFormatter.ofPattern("dd MMMM").format(DateTimeFormatter.ofPattern("dd/MM/yyyy").parse(riwayat_item.tanggal))
            val tahun = DateTimeFormatter.ofPattern("yyyy").format(DateTimeFormatter.ofPattern("dd/MM/yyyy").parse(riwayat_item.tanggal))

            holder.card.tv_tanggal.text = tanggal
            holder.card.tv_tahun.text = tahun
        }
    }


    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): RiwayatPembayaranViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.riwayat_pembayaran_card, parent, false)
        return RiwayatPembayaranViewHolder(
            view
        )
    }

    override fun getItemCount() = riwayat.size


}