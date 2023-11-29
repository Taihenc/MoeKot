package com.taihen.moekot.ui.moeGrid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.taihen.moekot.R
import com.taihen.moekot.model.MoeItem

class MoeAdapter(private var moeItems: List<MoeItem>) : RecyclerView.Adapter<MoeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.moe_grid_item, parent, false)
        return MoeViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoeViewHolder, position: Int) {
        holder.bind(moeItems[position])
        holder.itemView.setOnClickListener {
            println(it)
        }
    }

    fun updateData(newMoeList: List<MoeItem>) {
        moeItems = newMoeList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = moeItems.size
}

