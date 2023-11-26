package com.taihen.moekot

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.taihen.moekot.model.MoeItem

class MoeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val imageView: ImageView = itemView.findViewById(R.id.moe_image)
    private val titleTextView: TextView = itemView.findViewById(R.id.moe_title)
    private val subTitleTextView: TextView = itemView.findViewById(R.id.moe_sub_title)

    fun bind(moeItem: MoeItem) {
        imageView.setImageResource(moeItem.imageResId)
        titleTextView.text = moeItem.title
        subTitleTextView.text = moeItem.title
    }
    fun setItemHeight(height: Int) {
        val layoutParams = itemView.layoutParams
        layoutParams.height = height
        itemView.layoutParams = layoutParams
    }
}
