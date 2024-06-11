package com.politecnico.quizap.data

data class Question(
    val text: String,
    val answers: List<String>,
    val correctAnswer: Int
)
