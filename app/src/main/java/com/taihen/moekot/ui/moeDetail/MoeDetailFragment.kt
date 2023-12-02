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
import com.taihen.moekot.databinding.FragmentMoeDetailBinding
import com.taihen.moekot.model.MoeDetailViewModel
import com.taihen.moekot.model.MoeItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MoeDetailFragment(): Fragment(R.layout.fragment_moe_detail) {
    @Inject
    lateinit var selectedMoeItem: MoeDetailViewModel

    private lateinit var _binding: FragmentMoeDetailBinding

    private lateinit var _moeItem: MoeItem

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoeDetailBinding.inflate(inflater, container, false)
        val view = _binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectedMoeItem.moeItem.observe(viewLifecycleOwner) {
            _moeItem = it
            updateUI()
        }
    }
    private fun updateUI() {
        _binding.moeBackdrop.load(_moeItem.backdropImageUri)
        _binding.moeCardCover.load(_moeItem.coverImageUri)
        _binding.moeDetailTitle.text = _moeItem.title
        _binding.moeDetailAuthor.text = _moeItem.author
        _binding.moeDetailStatus.text = _moeItem.status
        _binding.moeDetailDescriptionTitle.text = getString(R.string.moe_detail_description_title, _moeItem.type)
        _binding.moeDetailDescription.text = _moeItem.description

        _binding.moeDetailGenres.removeAllViews()
        _moeItem.genres.forEach { genre ->
            val chip = Chip(requireContext())
            chip.text = genre
            chip.isClickable = false
            _binding.moeDetailGenres.addView(chip)
        }
    }
}
