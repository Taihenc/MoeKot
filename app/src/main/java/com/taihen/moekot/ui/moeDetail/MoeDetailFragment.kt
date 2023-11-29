package com.taihen.moekot.ui.moeDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.taihen.moekot.R

class MoeDetailFragment: Fragment(R.layout.fragment_moe_detail) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_moe_detail, container, false)
    }
}
