package com.platzi.conf.view.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.platzi.conf.R
import com.platzi.conf.model.Pinjaman
import kotlinx.android.synthetic.main.riwayat_peminjaman_card.view.*
import java.time.format.DateTimeFormatter

class RiwayatPeminjamanAdapter(val riwayat : MutableList<Pinjaman>) : RecyclerView.Adapter<RiwayatPeminjamanAdapter.RiwayatPeminjamanViewHolder>() {

    class RiwayatPeminjamanViewHolder(val card : View) : RecyclerView.ViewHolder(card)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RiwayatPeminjamanViewHolder, position: Int) {
        if (holder != null){
            val riwayat_item = riwayat[position]

            if (riwayat_item.jenis.equals("uang_kuliah")){
                holder.card.tv_image.setImageResource(R.drawable.ic_college_graduation_blue)
                holder.card.tv_jenis.text = "Uang Kuliah"
            } else {
                holder.card.tv_image.setImageResource(R.drawable.ic_give_money_blue)
                holder.card.tv_jenis.text = "Uang Bulanan"
            }

            holder.card.tv_nominal.text = "Rp " + riwayat_item.nominalPengajuan

            val tanggal = DateTimeFormatter.ofPattern("dd MMMM").format(DateTimeFormatter.ofPattern("dd/MM/yyyy").parse(riwayat_item.tanggalPengajuan))
            val tahun = DateTimeFormatter.ofPattern("yyyy").format(DateTimeFormatter.ofPattern("dd/MM/yyyy").parse(riwayat_item.tanggalPengajuan))

            holder.card.tv_tanggal.text = tanggal
            holder.card.tv_tahun.text = tahun
        }
    }


    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): RiwayatPeminjamanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.riwayat_peminjaman_card, parent, false)
        return RiwayatPeminjamanViewHolder(
            view
        )
    }

    override fun getItemCount() = riwayat.size


}