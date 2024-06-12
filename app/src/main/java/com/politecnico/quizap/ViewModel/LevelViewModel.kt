package com.politecnico.quizap.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.politecnico.quizap.data.Model.Level

class LevelViewModel private constructor() : ViewModel() {
    private val _listLevel = MutableLiveData<List<Level>>()
    val listLevel: LiveData<List<Level>> = _listLevel


    fun setLevels(levels : List<Level>) {
        val prueba = listOf<Level>(
            Level(1,1,4,"L1"),
            Level(2,1,4,"L2"),
            Level(3,1,4,"L3"),
            Level(4,1,4,"L4"),
            Level(5,1,4,"L5"),
            Level(6,1,4,"L6"),
            Level(7,1,4,"L7"),
            Level(8,1,4,"L8"),
            Level(9,1,4,"D9")
        )
        _listLevel.value = prueba
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