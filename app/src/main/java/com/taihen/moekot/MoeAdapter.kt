package com.taihen.moekot

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.taihen.moekot.model.MoeItem
import dpToPx

class MoeAdapter(private val moeItems: List<MoeItem>) : RecyclerView.Adapter<MoeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.moe_grid_item, parent, false)
//        view.setH = 270.dpToPx
        println("view: $view")
        return MoeViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoeViewHolder, position: Int) {
        holder.bind(moeItems[position])
    }

    override fun getItemCount(): Int = moeItems.size
}
