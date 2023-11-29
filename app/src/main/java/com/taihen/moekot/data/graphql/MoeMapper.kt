package com.taihen.moekot.data.graphql

import android.net.Uri
import com.anilist.graphql.GetMoeDataQuery
import com.anilist.graphql.fragment.MediaFragment
import com.anilist.graphql.type.Media
import com.taihen.moekot.model.MoeItem

fun MediaFragment.toMoeItem(): MoeItem {
    return MoeItem(
        imageUri = coverImage?.large?.let { Uri.parse(it) } ?: Uri.EMPTY,
        title = title?.userPreferred ?: "",
        author = staff?.nodes?.firstOrNull()?.name?.userPreferred ?: "",
        description = description ?: "",
        genre = genres?.map { it ?: "" } ?: emptyList(),
        status = status?.name ?: ""
    )
}
