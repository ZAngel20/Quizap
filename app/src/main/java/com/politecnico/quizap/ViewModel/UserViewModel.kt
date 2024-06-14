package com.politecnico.quizap.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.politecnico.quizap.data.Model.User
import com.politecnico.quizap.data.Model.UserName

class UserViewModel private constructor() : ViewModel() {
    private val _id = MutableLiveData<String>()
    private val _email = MutableLiveData<String>()
    private val _userName = MutableLiveData<String>()

    val id: LiveData<String> = _id
    val email: LiveData<String> = _email
    val userName: LiveData<String> = _userName

    fun setUser(user: User) {
        _id.value = user.id
        _email.value = user.email
        _userName.value = user.userName
    }
    fun changeUserName(userName: UserName) {
        _userName.value = userName.userName
    }
    fun logout() {
        _id.value = "-"
        _email.value = "-"
        _userName.value = "-"
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