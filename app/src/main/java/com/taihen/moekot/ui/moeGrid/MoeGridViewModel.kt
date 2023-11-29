package com.taihen.moekot.ui.moeGrid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anilist.graphql.type.MediaType
import com.apollographql.apollo3.exception.ApolloException
import com.taihen.moekot.data.graphql.toMoeItem
import com.taihen.moekot.model.AnilistClient
import com.taihen.moekot.model.MoeItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoeViewModel @Inject constructor(
    private val anilistClient: AnilistClient
) : ViewModel() {

    private val _moeItemList = MutableLiveData<List<MoeItem>>()
    val moeItemList: LiveData<List<MoeItem>> get() = _moeItemList

    fun fetchMoeItems() {
        viewModelScope.launch {
            try {
                val mediaList = anilistClient.getPopularMedia(MediaType.ANIME, 1, 50)
                val moeList = mediaList.map { media -> media.toMoeItem() }
                _moeItemList.value = moeList
            } catch (e: ApolloException) {
                e.printStackTrace()
            }
        }
    }
}
