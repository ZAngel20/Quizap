package com.politecnico.quizap.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProfileScreenViewModel : ViewModel() {
    private val _username = MutableLiveData<String>()
    private val _email = MutableLiveData<String>()
    private val _score = MutableLiveData<Int>()
    private val _ranking = MutableLiveData<Int?>()

    val username: String get() = _username.value?: "Angel"
    val email: String get() = _email.value?: "Angel@gmail.com"
    val score: Int get() = _score.value?: 0
    val ranking: Int? get() = _ranking.value

    init {
        loadProfile()
    }

    private fun loadProfile() {
        val name = "Angel"
        val email = "Angel@gmail.com"
        val score = 999
        val ranking = null


        viewModelScope.launch {
            delay(1000)
            _username.value = name
            _email.value = email
            _score.value = score
            _ranking.value = ranking

        }
    }
}