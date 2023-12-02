package com.taihen.moekot.ui.moeGrid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.taihen.moekot.R
import com.taihen.moekot.model.MoeItem
import com.taihen.moekot.model.MoeDetailViewModel
import javax.inject.Inject

class MoeAdapter @Inject constructor(
    private var moeItems: List<MoeItem>,
    private val selectedMoeItem: MoeDetailViewModel
): ListAdapter<MoeItem, MoeGridViewHolder>(MoeItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoeGridViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.moe_grid_item, parent, false)
        return MoeGridViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoeGridViewHolder, position: Int) {
        holder.bind(moeItems[position])
        holder.itemView.setOnClickListener {
            selectedMoeItem.updateMoeItem(moeItems[position])
            findNavController(holder.itemView).navigate(R.id.action_moeGridFragment_to_moeDetailFragment)
        }
    }

    fun updateData(newMoeList: List<MoeItem>) {
        moeItems = newMoeList
        submitList(newMoeList)
    }

    override fun getItemCount(): Int = moeItems.size
}

class MoeItemDiffCallback : DiffUtil.ItemCallback<MoeItem>() {
    override fun areItemsTheSame(oldItem: MoeItem, newItem: MoeItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MoeItem, newItem: MoeItem): Boolean {
        return oldItem == newItem
    }
}
