package com.yasir.scrollontop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_main.view.*

class MainAdapter(private val modelMains: ArrayList<ModelMain>): RecyclerView.Adapter<MainAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item_main, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val modelMain = modelMains[position]
        holder.tvName.text = modelMain.name
        holder.tvJam.text = modelMain.jam
        holder.tvAlamat.text = modelMain.alamat
    }

    override fun getItemCount(): Int {
        return modelMains.size
    }

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var tvName: TextView
        var tvJam: TextView
        var tvAlamat: TextView

        init {
            tvName = itemView.tvName
            tvJam = itemView.tvJam
            tvAlamat = itemView.tvAlamat
        }
    }
}