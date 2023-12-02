package com.taihen.moekot

import com.apollographql.apollo3.ApolloClient
import com.taihen.moekot.data.graphql.MoeClient
import com.taihen.moekot.model.MoeListViewModel
import com.taihen.moekot.model.MoeDetailViewModel
import com.taihen.moekot.ui.moeGrid.MoeAdapter
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(moeAdapter: MoeAdapter)
    // Add other injection functions for your activities/fragments/viewmodels if needed
}
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApolloClient(): com.apollographql.apollo3.ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://graphql.anilist.co")
            .build()
    }

    @Provides
    @Singleton
    fun provideMoeClient(apolloClient: com.apollographql.apollo3.ApolloClient): MoeClient {
        return com.taihen.moekot.data.graphql.MoeClientImpl(apolloClient)
    }

    @Provides
    @Singleton
    fun provideMoeShareViewModel(): MoeDetailViewModel {
        return MoeDetailViewModel()
    }

    @Provides
    @Singleton
    fun provideMoeListViewModel(): MoeListViewModel {
        return MoeListViewModel()
    }
}
