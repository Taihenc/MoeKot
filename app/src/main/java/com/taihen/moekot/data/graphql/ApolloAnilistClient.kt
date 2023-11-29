package com.taihen.moekot.data.graphql

import android.net.Uri
import com.anilist.graphql.GetMoeDataQuery
import com.anilist.graphql.fragment.MediaFragment
import com.anilist.graphql.type.Media
import com.anilist.graphql.type.MediaType
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.taihen.moekot.model.AnilistClient
import javax.inject.Singleton

@Singleton
class ApolloAnilistClient(
    private val apolloClient: ApolloClient
): AnilistClient {
    override suspend fun getPopularMedia(type: MediaType, page: Int, perPage: Int): List<MediaFragment> {
        val res = apolloClient.query(
            GetMoeDataQuery(
                Optional.present(type),
                Optional.present(page),
                Optional.present(perPage)
            )
        ).execute()
        return res.data?.trending?.media?.mapNotNull { it?.mediaFragment } ?: emptyList()
    }
}
