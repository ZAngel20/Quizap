package com.politecnico.quizap.data.Model

import android.util.Log
import com.politecnico.quizap.data.Model.API.MiAPI

class GameRepository(
    private val api: MiAPI
) {
    suspend fun getCategories(): Result<List<Category>> {
        try {
            Log.d("AntesLlamada:" , "Se está llamando")
            val response = api.getCategories()
            Log.d("DespuesLlamada:" ,response.toString())
            return Result.success(response)
        } catch (e : Exception) {
            Log.d("ErrorLlamada:" ,e.message?: "No hay Texto de Error")
            val errorMessage = e.message?: "Error desconocido"
            return Result.failure(RuntimeException(errorMessage))
        }
    }
    suspend fun getLevels(category: CategoryDto ): Result<List<Level>> {
        try {
            Log.d("AntesLlamada:" , "Se está llamando")
            val response = api.getLevels(category)
            Log.d("DespuesLlamada:" ,response.toString())
            return Result.success(response)
        } catch (e : Exception) {
            Log.d("ErrorLlamada:" ,e.message?: "No hay Texto de Error")
            val errorMessage = e.message?: "Error desconocido"
            return Result.failure(RuntimeException(errorMessage))
        }
    }
    suspend fun getQuestions(level: LevelDto): Result<List<Question>> {
        try {
            Log.d("AntesLlamada:" , "Se está llamando")
            val response = api.getQuestions(level)
            Log.d("DespuesLlamada:" ,response.toString())
            return Result.success(response)
        } catch (e : Exception) {
            Log.d("ErrorLlamada:" ,e.message?: "No hay Texto de Error")
            val errorMessage = e.message?: "Error desconocido"
            return Result.failure(RuntimeException(errorMessage))
        }
    }
    suspend fun getAnswer(question: QuestionDto): Result<Answer> {
        try {
            Log.d("AntesLlamada:" , "Se está llamando")
            val response = api.getAnswer(question)
            Log.d("DespuesLlamada:" ,response.toString())
            return Result.success(response)
        } catch (e : Exception) {
            Log.d("ErrorLlamada:" ,e.message?: "No hay Texto de Error")
            val errorMessage = e.message?: "Error desconocido"
            return Result.failure(RuntimeException(errorMessage))
        }
    }
}

