package com.politecnico.quizap.data.Model

data class Question(
    val id : Int,
    val idLevel  : Int,
    val text: String,
    val index: Int,
    val answers: List<Answer>,
    val correctAnswer: Int
)
data class QuestionDto(
    val id: Int
)