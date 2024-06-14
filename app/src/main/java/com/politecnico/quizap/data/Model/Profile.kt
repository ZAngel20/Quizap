package com.politecnico.quizap.data.Model

data class Profile(
    val name: String = "-",
    val email: String = "-",
    val score: Int = 0,
    val ranking: Int? = null
)
