package com.politecnico.quizap.data.Model

data class User(
    val activatedDate: String,
    val activationToken: Any,
    val id: String,
    val email: String,
    val status: String,
    val type: String,
    val userName: String
)
data class UserName(
    val userName: String
)
data class UserPass(
    val email: String,
    val token: String,
    val newPasswd: String
)
data class UserDto(
    val userName: String,
    val email: String,
    val passwd: String
)
data class UserToken(
    val email: String,
    val token: String
)
data class UserLogin(
    val email: String,
    val passwd: String
)
data class UserAccessToken(
    val accessToken: String
)

data class UserResend(
    val email: String
)