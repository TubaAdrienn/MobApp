package com.example.loveapp.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loveapp.dao.LoveDao
import com.example.loveapp.lovelist.LoveListViewModel

class DetailViewModelFactory(
    private val dataSource: LoveDao,
    private val loveId: Long
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(dataSource,loveId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}