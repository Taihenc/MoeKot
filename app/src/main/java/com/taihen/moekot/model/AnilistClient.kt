package com.taihen.moekot.model

import com.anilist.graphql.fragment.MediaFragment
import com.anilist.graphql.type.MediaType

interface AnilistClient {
    suspend fun getPopularMedia(type: MediaType, page: Int, perPage: Int): List<MediaFragment>
//    suspend fun getTrendingMedia(page: Int, perPage: Int): List<Media>
}
