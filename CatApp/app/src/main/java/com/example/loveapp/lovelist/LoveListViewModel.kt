package com.example.loveapp.lovelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loveapp.dao.LoveDao

class LoveListViewModel(dataSource: LoveDao
) : ViewModel() {

    val database = dataSource

    val results = database.getAllResults()

    private val _navigateToDetail = MutableLiveData<Long>()
    val navigateToDetail: LiveData<Long>
        get() = _navigateToDetail

    fun onLoveResultClicked(id: Long) {
        _navigateToDetail.value = id
    }

    fun onDetailNavigated() {
        _navigateToDetail.value = null
    }
}