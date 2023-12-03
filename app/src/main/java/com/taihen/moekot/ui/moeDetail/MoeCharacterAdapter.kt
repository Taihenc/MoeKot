package com.taihen.moekot.ui.moeDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.taihen.moekot.databinding.MoeCharacterItemBinding
import com.taihen.moekot.model.MoeCharacter
import com.taihen.moekot.model.MoeDetailViewModel
import javax.inject.Inject

class MoeCharacterAdapter(private var moeCharacters: List<MoeCharacter>): ListAdapter<MoeCharacter, MoeCharacterViewHolder>(MoeCharacterDiffCallback())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoeCharacterViewHolder {
        val view = MoeCharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoeCharacterViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: MoeCharacterViewHolder, position: Int) {
        holder.bind(moeCharacters[position])
    }
    override fun getItemCount(): Int = currentList.size
    fun updateData(newMoeCharacterList: List<MoeCharacter>) {
        moeCharacters = newMoeCharacterList
        submitList(newMoeCharacterList)
    }
}

class MoeCharacterDiffCallback: DiffUtil.ItemCallback<MoeCharacter>() {
    override fun areItemsTheSame(oldItem: MoeCharacter, newItem: MoeCharacter): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MoeCharacter, newItem: MoeCharacter): Boolean {
        return oldItem == newItem
    }
}
