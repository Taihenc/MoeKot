package com.taihen.moekot.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.taihen.moekot.model.MoeItem
import javax.inject.Inject

class MoeDetailViewModel: ViewModel() {
    private val _moeItem = MutableLiveData<MoeItem>()
    val moeItem: LiveData<MoeItem> get() = _moeItem
    fun updateMoeItem(newItem: MoeItem) {
        _moeItem.value = newItem
    }
}
