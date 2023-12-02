package com.taihen.moekot.ui.moeGrid

import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.taihen.moekot.R
import com.taihen.moekot.model.MoeItem
import coil.load
import coil.transform.CircleCropTransformation
import com.taihen.moekot.databinding.MoeGridItemBinding

class MoeGridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val _binding: MoeGridItemBinding = MoeGridItemBinding.bind(itemView)
    fun bind(moeItem: MoeItem) {
        // Load the image with Coil
        _binding.moeImage.load(moeItem.coverImageUri) {
            crossfade(true)
        }
        _binding.moeTitle.text = moeItem.title
        _binding.moeSubTitle.text = moeItem.author
        _binding.moeSubTitle.visibility = if (_binding.moeTitle.lineCount > 1) View.GONE else View.VISIBLE
    }
    fun setItemHeight(height: Int) {
        val layoutParams = itemView.layoutParams
        layoutParams.height = height
        itemView.layoutParams = layoutParams
    }
}
