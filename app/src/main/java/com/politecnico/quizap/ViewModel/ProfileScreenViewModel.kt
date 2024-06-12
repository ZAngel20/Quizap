package com.politecnico.quizap.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.politecnico.quizap.data.Model.Profile

class ProfileScreenViewModel private constructor() : ViewModel() {
    private val _username = MutableLiveData<String>()
    private val _email = MutableLiveData<String>()
    private val _score = MutableLiveData<Int>()
    private val _ranking = MutableLiveData<Int?>()

    val username: LiveData<String>  = _username
    val email: LiveData<String>  = _email
    val score: LiveData<Int>  = _score
    val ranking: LiveData<Int?>  = _ranking

        fun setProfile(profile : Profile) {
            _username.value = profile.name
            _email.value = profile.email
            _score.value = profile.score
            _ranking.value = profile.ranking
    }
    companion object {
        private var instance: ProfileScreenViewModel? = null

        fun getInstance(): ProfileScreenViewModel {
            if (instance == null) {
                instance = ProfileScreenViewModel()
            }
            return instance!!
        }
    }
}