package com.taihen.moekot.ui.moeDetail

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.dispose
import coil.load
import com.taihen.moekot.databinding.MoeCharacterItemBinding
import com.taihen.moekot.model.MoeCharacter
import com.taihen.moekot.model.MoeStaff

class MoeStaffViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val _binding: MoeCharacterItemBinding = MoeCharacterItemBinding.bind(itemView)
    fun bind(moeStaff: MoeStaff) {
        // Load the image with Coil
        _binding.moeCharacterImage.dispose()
        _binding.moeCharacterImage.load(moeStaff.mediumImageUri) {
            crossfade(true)
        }
        _binding.moeCharacterName.text = moeStaff.name
    }
}
