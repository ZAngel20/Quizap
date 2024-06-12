package com.politecnico.quizap.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.politecnico.quizap.data.Model.User

class UserViewModel private constructor() : ViewModel() {
    private val _activatedDate = MutableLiveData<String>()
    private val _activationToken = MutableLiveData<Any>()
    private val _id = MutableLiveData<String>()
    private val _email = MutableLiveData<String>()
    private val _status = MutableLiveData<String>()
    private val _type = MutableLiveData<String>()
    private val _userName = MutableLiveData<String>()

    val activatedDate: LiveData<String> = _activatedDate
    val activationToken: LiveData<Any> = _activationToken
    val id: LiveData<String> = _id
    val email: LiveData<String> = _email
    val status: LiveData<String> = _status
    val type: LiveData<String> = _type
    val userName: LiveData<String> = _userName

    fun setUser(user: User) {
        _activatedDate.value = user.activatedDate
        _activationToken.value = user.activationToken
        _id.value = user.id
        _email.value = user.email
        _status.value = user.status
        _type.value = user.type
        _userName.value = user.userName
    }

    companion object {
        private var instance: UserViewModel? = null

        fun getInstance(): UserViewModel {
            if (instance == null) {
                instance = UserViewModel()
            }
            return instance!!
        }
    }
}