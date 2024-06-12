package com.politecnico.quizap.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.politecnico.quizap.data.Model.Category

class CategoriesScreenViewModel private constructor() : ViewModel() {
    private val _listCategory = MutableLiveData<List<Category>>()
    val listCategory: LiveData<List<Category>> = _listCategory


    fun setCategories(categories : List<Category>) {
        val prueba = listOf<Category>(
            Category(1,"Prueba"),
            Category(2,"Llorando"),
            Category(3,"Prueba 2"),
            Category(4,"Llorando 2"),

        )
        _listCategory.value = prueba
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