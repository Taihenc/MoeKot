package com.taihen.moekot.data.graphql

import com.anilist.graphql.fragment.MediaFragment
import com.anilist.graphql.type.MediaType
import com.taihen.moekot.model.MoeItem

interface MoeClient {
    suspend fun getPopularMedia(type: MediaType, page: Int, perPage: Int): List<MediaFragment>

    suspend fun fetchMangaItems(page: Int, perPage: Int): List<MoeItem> {
        return getPopularMedia(MediaType.MANGA, page, perPage).map { media -> media.toMoeItem() }
    }
//    suspend fun fetchMoeItems(, page: Int, perPage: Int): List<MoeItem> {
//        return getPopularMedia(MediaType.ANIME, 1, 50).map { media -> media.toMoeItem() }
//    }
//    suspend fun getTrendingMedia(page: Int, perPage: Int): List<Media>
}
