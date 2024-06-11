package com.politecnico.quizap.data

sealed class ApiResult<T> {
    class Success<T>(val data: T) : ApiResult<T>()
    class Failed<T>(val exception: Exception) : ApiResult<T>()
}

