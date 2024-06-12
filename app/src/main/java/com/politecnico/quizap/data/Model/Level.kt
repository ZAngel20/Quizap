package com.politecnico.quizap.data.Model

data class Level(
    val id: Int,
    val idCategory: Int,
    val questions: Int,
    val name: String
)
data class LevelDto(
    val id: Int
)