package com.taihen.moekot.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taihen.moekot.data.graphql.MoeClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class MoeListViewModel @Inject constructor(
    private val _moeClient: MoeClient
): ViewModel() {
    private val _moeList = mutableListOf<MoeItem>()
    private val _moeItemList = MutableStateFlow<List<MoeItem>>(emptyList())
    val moeItemList = _moeItemList.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading
    private fun updateMoeList(moeList: List<MoeItem>) {
        _moeItemList.value = moeList.let { ArrayList(it) }
    }
    fun fetchMoreMoeItems(page: Int, pageSize: Int) {
        viewModelScope.launch {
            try {
                setIsLoading(true)
                val newList = _moeClient.fetchMangaItems(page, pageSize)
                _moeList.addAll(newList)
                updateMoeList(_moeList)
            } catch (e: Exception) {
                println(e)
            } finally {
                setIsLoading(false)
            }
        }
    }
    fun setIsLoading(isLoading: Boolean) {
        _loading.value = isLoading
    }
}

