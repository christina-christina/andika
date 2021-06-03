package com.platzi.conf.view.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.platzi.conf.R
import kotlinx.android.synthetic.main.riwayat_peminjaman_card.view.*

class RiwayatPeminjamanAdapter(val riwayat : List<RiwayatPeminjaman>) : RecyclerView.Adapter<RiwayatPeminjamanAdapter.RiwayatPeminjamanViewHolder>() {

    class RiwayatPeminjamanViewHolder(val card : View) : RecyclerView.ViewHolder(card)

    override fun onBindViewHolder(holder: RiwayatPeminjamanViewHolder, position: Int) {
        if (holder != null){
            val riwayat_item = riwayat[position]
            holder.card.tv_image.setImageResource(riwayat_item.imgId)
            holder.card.tv_jenis.text = riwayat_item.jenis
            holder.card.tv_nominal.text = riwayat_item.nominal
            holder.card.tv_tanggal.text = riwayat_item.tanggal
            holder.card.tv_bulan.text = riwayat_item.bulan
        }
    }


    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): RiwayatPeminjamanAdapter.RiwayatPeminjamanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.riwayat_peminjaman_card, parent, false)
        return RiwayatPeminjamanViewHolder(view)
    }

    override fun getItemCount() = riwayat.size


}