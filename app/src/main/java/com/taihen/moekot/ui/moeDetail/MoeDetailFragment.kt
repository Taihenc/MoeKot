package com.taihen.moekot.ui.moeDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import coil.load
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.taihen.moekot.R
import com.taihen.moekot.model.MoeDetailViewModel
import com.taihen.moekot.model.MoeItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MoeDetailFragment(): Fragment(R.layout.fragment_moe_detail) {
    @Inject
    lateinit var selectedMoeItem: MoeDetailViewModel

    private lateinit var _moeItem: MoeItem

    private lateinit var backdropImageView: ImageView
    private lateinit var cardCoverImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var authorTextView: TextView
    private lateinit var statusTextView: TextView
    private lateinit var descriptionTitleMoeType: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var chipGroup: ChipGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_moe_detail, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backdropImageView = view.findViewById(R.id.moe_backdrop)
        cardCoverImageView = view.findViewById(R.id.moe_card_cover)
        titleTextView = view.findViewById(R.id.moe_detail_title)
        authorTextView = view.findViewById(R.id.moe_detail_author)
        statusTextView = view.findViewById(R.id.moe_detail_status)
        descriptionTitleMoeType = view.findViewById(R.id.moe_detail_description_title)
        descriptionTextView = view.findViewById(R.id.moe_detail_description)
        chipGroup = view.findViewById(R.id.moe_detail_genres)

        selectedMoeItem.moeItem.observe(viewLifecycleOwner) {
            _moeItem = it
            updateUI()
        }
    }
    private fun updateUI() {
        backdropImageView.load(_moeItem.backdropImageUri)
        cardCoverImageView.load(_moeItem.coverImageUri)
        titleTextView.text = _moeItem.title
        authorTextView.text = _moeItem.author
        statusTextView.text = _moeItem.status
        descriptionTitleMoeType.text = getString(R.string.moe_detail_description_title, _moeItem.type)
        descriptionTextView.text = _moeItem.description

        chipGroup.removeAllViews()
        _moeItem.genres.forEach { genre ->
            val chip = Chip(requireContext())
            chip.text = genre
            chip.isClickable = false
            chipGroup.addView(chip)
        }
    }
}
