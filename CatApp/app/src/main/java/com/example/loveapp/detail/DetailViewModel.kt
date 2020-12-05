package com.example.loveapp.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loveapp.dao.LoveDao
import com.example.loveapp.dao.LoveResultSave

class DetailViewModel(dataSource: LoveDao, loveId:Long
) : ViewModel() {

    val database = dataSource

    private val love: LiveData<LoveResultSave>

    fun getLove() = love


    init {
        love=database.getResultWithId(loveId)
    }
}