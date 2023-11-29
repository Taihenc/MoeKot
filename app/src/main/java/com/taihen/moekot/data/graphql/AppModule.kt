package com.taihen.moekot.data.graphql

import com.apollographql.apollo3.ApolloClient
import com.taihen.moekot.ui.moeGrid.MoeViewModel
import com.taihen.moekot.model.AnilistClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://graphql.anilist.co")
            .build()
    }

    @Provides
    @Singleton
    fun provideAnilistClient(apolloClient: ApolloClient): AnilistClient {
        return ApolloAnilistClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideMoeViewModel(anilistClient: AnilistClient): MoeViewModel {
        return MoeViewModel(anilistClient)
    }
}

