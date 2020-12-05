package com.example.loveapp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loveapp.dao.LoveDao
import com.example.loveapp.dao.LoveResultSave
import com.example.loveapp.loveapi.LoveApi
import com.example.loveapp.loveapi.LoveResult
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }


class CalculatorViewModel(dataSource: LoveDao
) : ViewModel() {

    private val _navigateToListView = MutableLiveData<Boolean>()
    val navigateToListView: LiveData<Boolean>
        get() = _navigateToListView

    fun onSaveClicked(id: Boolean) {
        _navigateToListView.value = id
    }

    var name1 = ""
    var name2 = ""

    val database = dataSource

    private val _status = MutableLiveData<ApiStatus>()

    val status: LiveData<ApiStatus>
        get() = _status

    private val _result = MutableLiveData<LoveResult>()

    val properties: LiveData<LoveResult>
        get() = _result


    fun saveResult() : Boolean{
        if(_result.value!=null){
            viewModelScope.launch {
                var result = LoveResultSave()
                result.sname= _result.value?.sname ?: ""
                result.fname= _result.value?.fname ?: ""
                result.percentage= _result.value?.percentage ?: 0
                result.result   = _result.value?.result ?: ""

                database.insert(result)
            }
            return true
        }
        return false
    }

    fun getResult() {
        if (!name1.equals("") && !name2.equals("")) {
            viewModelScope.launch {
                _status.value = ApiStatus.LOADING
                try {
                    _result.value =
                        LoveApi.loveService.getLoveResult(name1 as String, name2 as String)
                    _status.value = ApiStatus.DONE
                } catch (e: Exception) {
                    _status.value = ApiStatus.ERROR
                    _result.value = null
                }
            }
        }
    }
}