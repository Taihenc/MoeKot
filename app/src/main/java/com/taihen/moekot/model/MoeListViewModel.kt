package com.taihen.moekot.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoeListViewModel: ViewModel() {
    private val _moeItemList = MutableLiveData<List<MoeItem>>()
    val moeItemList: LiveData<List<MoeItem>> get() = _moeItemList

    fun updateMoeList(newList: List<MoeItem>) {
        _moeItemList.value = newList
    }
}

