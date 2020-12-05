package com.example.loveapp.lovelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loveapp.calculator.CalculatorViewModel
import com.example.loveapp.dao.LoveDao

class LoveListViewModelFactory(
    private val dataSource: LoveDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoveListViewModel::class.java)) {
            return LoveListViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}