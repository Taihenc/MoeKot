package com.taihen.moekot.ui.moeGrid

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.taihen.moekot.R
import com.taihen.moekot.model.MoeItem
import coil.load
import coil.transform.CircleCropTransformation

class MoeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val imageView: ImageView = itemView.findViewById(R.id.moe_image)
    private val titleTextView: TextView = itemView.findViewById(R.id.moe_title)
    private val subTitleTextView: TextView = itemView.findViewById(R.id.moe_sub_title)

    fun bind(moeItem: MoeItem) {
        val layoutParams = imageView.layoutParams
        val scaleType = imageView.scaleType
        val adjustViewBound = imageView.adjustViewBounds

        // Load the image with Coil
        imageView.load(moeItem.imageUri) {
            crossfade(true)
//            transformations(CircleCropTransformation())
            listener(onSuccess = { _, _ ->
                imageView.layoutParams = layoutParams
                imageView.scaleType = scaleType
                imageView.adjustViewBounds = adjustViewBound
            })
        }
        titleTextView.text = moeItem.title
        subTitleTextView.text = moeItem.author
        titleTextView.post {
            if(titleTextView.lineCount > 1) {
                subTitleTextView.visibility = View.GONE
            } else {
                subTitleTextView.visibility = View.VISIBLE
            }
        }
    }
    fun setItemHeight(height: Int) {
        val layoutParams = itemView.layoutParams
        layoutParams.height = height
        itemView.layoutParams = layoutParams
    }
}
