package com.politecnico.quizap.data.Model


data class Question(
    val id : Int,
    val idAnswer: Int,
    val text: String,
    val index: Int,
    var answers: List<Answer>
)
data class PreQuestion(
    val id : Int,
    val idAnswer: Int,
    val text: String,
    val index: Int,
)
data class ResQuestion(
    val idQuestion : Int,
    val idAnswer: Int,
)

data class QuestionDto(
    val id: Int
)