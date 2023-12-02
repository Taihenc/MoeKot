package com.taihen.moekot.ui.moeDetail

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
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
import com.taihen.moekot.databinding.GenreChipBinding
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
            val chip = GenreChipBinding.inflate(layoutInflater).root as Chip
            chip.text = genre
            chip.isClickable = false
            _binding.moeDetailGenres.addView(chip)
        }

        _binding.moeDescriptionContainer.setOnClickListener {
            _binding.moeDetailDescription.maxLines = if (_binding.moeDetailDescription.maxLines == 3) Int.MAX_VALUE else 3
            animateTextViewExpansion(_binding.moeDetailDescription)
        }
    }
}

private fun animateTextViewExpansion(textView: TextView) {
    val animationDuration = 1000L // You can adjust the duration as needed

    val originalHeight = textView.height
    textView.measure(
        View.MeasureSpec.makeMeasureSpec(textView.width, View.MeasureSpec.EXACTLY),
        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    )
    val targetHeight = textView.measuredHeight
    val prevMaxlines = if (targetHeight < originalHeight) textView.maxLines else Int.MAX_VALUE

    println("targetHeight: $targetHeight")
    println("originalHeight: $originalHeight")
    println("-------------------------------")

    val valueAnimator = ValueAnimator.ofInt(originalHeight, targetHeight)
    valueAnimator.duration = animationDuration
    valueAnimator.addUpdateListener { valueAnimator ->
        val animatedValue = valueAnimator.animatedValue as Int
        val layoutParams = textView.layoutParams
        layoutParams.height = animatedValue
        textView.layoutParams = layoutParams
        textView.maxLines = Int.MAX_VALUE
    }
    valueAnimator.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            textView.maxLines = prevMaxlines
        }
    })

    valueAnimator.start()
}


