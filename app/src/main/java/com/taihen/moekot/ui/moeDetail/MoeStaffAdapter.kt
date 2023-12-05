package com.taihen.moekot.ui.moeDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.taihen.moekot.databinding.MoeCharacterItemBinding
import com.taihen.moekot.model.MoeCharacter
import com.taihen.moekot.model.MoeStaff

class MoeStaffAdapter(private var moeStaff: List<MoeStaff>): ListAdapter<MoeStaff, MoeStaffViewHolder>(MoeStaffDiffCallBack())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoeStaffViewHolder {
        val view = MoeCharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoeStaffViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: MoeStaffViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class MoeStaffDiffCallBack: DiffUtil.ItemCallback<MoeStaff>() {
    override fun areItemsTheSame(oldItem: MoeStaff, newItem: MoeStaff): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MoeStaff, newItem: MoeStaff): Boolean {
        return oldItem == newItem
    }
}
