package com.taihen.moekot.model

import android.net.Uri

data class MoeItem(
    val id: Int,
    val coverImageUri: Uri,
    val backdropImageUri: Uri,
    val title: String,
    val author: String,
    val description: String,
    val genres: List<String>,
    val type: String,
    val status: String,
    val characters: List<MoeCharacter>
)
