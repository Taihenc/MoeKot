package com.taihen.moekot.model

import com.anilist.graphql.fragment.MediaFragment
import com.anilist.graphql.type.MediaType
import com.taihen.moekot.data.graphql.toMoeItem

interface AnilistClient {
    suspend fun getPopularMedia(type: MediaType, page: Int, perPage: Int): List<MediaFragment>

    suspend fun fetchMoeItems(typeInt :Int, page: Int, perPage: Int): List<MoeItem> {
        return getPopularMedia(MediaType.ANIME, 1, 50).map { media -> media.toMoeItem() }
    }
//    suspend fun getTrendingMedia(page: Int, perPage: Int): List<Media>
}
