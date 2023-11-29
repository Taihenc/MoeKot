package com.taihen.moekot.model

import android.net.Uri

data class MoeItem(
    val imageUri: Uri,
    val title: String,
    val author: String,
    val description: String,
    val genre: List<String>,
    val status: String
)
