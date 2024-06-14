package com.politecnico.quizap.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.politecnico.quizap.data.Model.Level

class LevelViewModel private constructor() : ViewModel() {
    private val _listLevel = MutableLiveData<List<Level>>()
    val listLevel: LiveData<List<Level>> = _listLevel


    fun setLevels(list : List<Level>) {
       _listLevel.value = list
    }
    companion object {
        private var instance: LevelViewModel? = null

        fun getInstance(): LevelViewModel {
            if (instance == null) {
                instance = LevelViewModel()
            }
            return instance!!
        }
    }
}