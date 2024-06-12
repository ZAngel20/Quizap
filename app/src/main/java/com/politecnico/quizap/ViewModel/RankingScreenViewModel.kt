package com.politecnico.quizap.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RankingScreenViewModel : ViewModel() {
    private val _username = MutableLiveData<String>()
    private val _score = MutableLiveData<Int>()
    private val _id= MutableLiveData<Int>()

    init {
        loadRanking()
    }

    private fun loadRanking() {
        // Fake API call to get list of categories


        // Simulate API call delay
        viewModelScope.launch {
            delay(1000)
        //  _levels.value = categories
        }
    }
}