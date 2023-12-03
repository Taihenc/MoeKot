package com.taihen.moekot.ui.moeDetail

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.taihen.moekot.databinding.MoeCharacterItemBinding
import com.taihen.moekot.model.MoeCharacter

class MoeCharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val _binding: MoeCharacterItemBinding = MoeCharacterItemBinding.bind(itemView)
    fun bind(moeCharacter: MoeCharacter) {
        // Load the image with Coil
        _binding.moeCharacterImage.load(moeCharacter.mediumImageUri) {
            crossfade(true)
        }
        _binding.moeCharacterName.text = moeCharacter.name
    }
}
