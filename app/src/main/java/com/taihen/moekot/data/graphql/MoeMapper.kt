package com.taihen.moekot.data.graphql

import android.net.Uri
import android.text.Html
import androidx.compose.ui.text.toLowerCase
import androidx.core.text.HtmlCompat
import com.anilist.graphql.fragment.MediaFragment
import com.taihen.moekot.model.MoeCharacter
import com.taihen.moekot.model.MoeItem
import com.taihen.moekot.model.MoeStaff
import java.util.Locale

// extension function to convert MediaFragment to MoeItem
fun MediaFragment.toMoeItem(): MoeItem {
    return MoeItem(
        id = id,
        backdropImageUri = bannerImage?.let { Uri.parse(it) } ?: Uri.EMPTY,
        coverImageUri = coverImage?.large?.let { Uri.parse(it) } ?: Uri.EMPTY,
        title = title?.userPreferred ?: "",
        author = staff?.nodes?.firstOrNull()?.name?.userPreferred ?: "",
        description = Html.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT).toString() ?: "",
        genres = genres?.map { it ?: "" } ?: emptyList(),
        type = this.type.toString().toFormatted(),
        status = status?.name ?: "",
        characters = characters?.toMoeCharacters() ?: emptyList(),
        staffs = staff?.toMoeStaffs() ?: emptyList()
    )
}

fun String.toFormatted(): String {
    return this[0].uppercaseChar() + this.substring(1).lowercase()
}

fun MediaFragment.Characters.toMoeCharacters(): List<MoeCharacter> {
    val list = nodes?.map {
        MoeCharacter(
            id = it!!.id,
            mediumImageUri = it.image?.medium ?: "",
            largeImageUri = it.image?.large ?: "",
            name = it.name?.userPreferred ?: ""
        )
    } ?: emptyList()
    return list
}

fun MediaFragment.Staff.toMoeStaffs(): List<MoeStaff> {
    val list = nodes?.map {
        MoeStaff(
            id = it!!.id,
            mediumImageUri = it.image?.medium ?: "",
            largeImageUri = it.image?.medium ?: "",
            name = it.name?.userPreferred ?: ""
        )
    } ?: emptyList()
    return list
}
