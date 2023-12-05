package com.taihen.moekot.data.graphql

import com.anilist.graphql.GetMoeDataQuery
import com.anilist.graphql.fragment.MediaFragment
import com.anilist.graphql.type.MediaType
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoeClientImpl @Inject constructor(private val apolloClient: ApolloClient): MoeClient {
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
