package com.politecnico.quizap.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.politecnico.quizap.data.Category
import com.politecnico.quizap.data.Level
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CategoriesScreenViewModel : ViewModel() {
    private val _levels = MutableLiveData<List<Category>>()
    val levels: LiveData<List<Category>> = _levels

    init {
        loadCategories()
    }

    private fun loadCategories() {
        // Fake API call to get list of categories
        val categories = listOf(
            Category("Animales", listOf(
                Level("level1", "Level 1"),
                Level("level2", "Level 2"),
                Level("level3", "Level 3"),
                Level("level4", "Level 4"),
                Level("level5", "Level 5")
            )),
            Category("Juegos", listOf(
                Level("level6", "Level 6"),
                Level("level7", "Level 7"),
                Level("level8", "Level 8"),
                Level("level9", "Level 9"),
                Level("level10", "Level 10")
            )),
            Category("Geografia", listOf(
                Level("level11", "Level 11"),
                Level("level12", "Level 12"),
                Level("level13", "Level 13"),
                Level("level14", "Level 14"),
                Level("level15", "Level 15")
            ))
        )

        // Simulate API call delay
        viewModelScope.launch {
            delay(1000)
            _levels.value = categories
        }
    }
}