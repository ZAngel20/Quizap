package com.politecnico.quizap.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.politecnico.quizap.data.Model.API.MiAPI
import com.politecnico.quizap.data.Model.Category
import com.politecnico.quizap.data.Model.GameRepository
import kotlinx.coroutines.launch

class CategoriesScreenViewModel private constructor() : ViewModel() {
    private val _listCategory = MutableLiveData<List<Category>>()
    val listCategory: LiveData<List<Category>> = _listCategory


    fun setCategories(context: Context) {
        val gameRepository: GameRepository by lazy { GameRepository(MiAPI.instance) }
        if (listCategory.value.isNullOrEmpty()) {
            viewModelScope.launch {
                val result: Result<List<Category>> = gameRepository.getCategories(context)
                try {
                    Log.d("Resultado Bueno:", result.toString())
                    val listCategories: List<Category>? = result.getOrNull()
                    if (listCategories != null) {
                        _listCategory.value = listCategories!!
                    }
                } catch (e: Exception) {
                    Log.d("Resultado Malo:", result.toString())
                    _listCategory.value = emptyList<Category>()

                }
            }
        }


    }
    companion object {
        private var instance: CategoriesScreenViewModel? = null

        fun getInstance(): CategoriesScreenViewModel {
            if (instance == null) {
                instance = CategoriesScreenViewModel()
            }
            return instance!!
        }
    }
}