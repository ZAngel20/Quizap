package com.politecnico.quizap.data.Model

import android.content.Context
import android.util.Log
import com.politecnico.quizap.data.Model.API.MiAPI
import com.politecnico.quizap.data.Model.Services.PreferenceHelper

class GameRepository(
    private val api: MiAPI
) {
    suspend fun getCategories(context : Context): Result<List<Category>> {
        val token = PreferenceHelper.getToken(context)
        try {
            val codeToken = "Bearer " + token
            Log.d("AntesLlamada:", codeToken)
            val response = api.getCategories(codeToken)
            return Result.success(response)
        } catch (e : Exception) {
            return Result.failure(e)
        }
    }
    suspend fun getLevels(context : Context,category: CategoryDto ): Result<List<Level>> {
        val token = PreferenceHelper.getToken(context)
        try {
            val codeToken = "Bearer " + token
            Log.d("AntesLlamada:", codeToken)
            val response = api.getLevels(codeToken,category.id)
            return Result.success(response)
        } catch (e : Exception) {
            return Result.failure(e)
        }
    }
    suspend fun getQuestions(context : Context,level: LevelDto): Result<List<PreQuestion>> {
        val token = PreferenceHelper.getToken(context)
        try {
            val codeToken = "Bearer " + token
            Log.d("AntesLlamada:", codeToken)
            val response = api.getQuestions(codeToken,level.id)
            return Result.success(response)
        } catch (e : Exception) {
            return Result.failure(e)
        }
    }
    suspend fun getAnswers(context : Context,question: QuestionDto): Result<List<Answer>> {
        val token = PreferenceHelper.getToken(context)
        try {
            val codeToken = "Bearer " + token
            Log.d("AntesLlamada:", codeToken)
            val response = api.getAnswers(codeToken,question.id)
            return Result.success(response)
        } catch (e : Exception) {
            return Result.failure(e)
        }
    }
    suspend fun evaluateLevel(context : Context,evaluateLevel: EvaluateLevel): Result<Int> {
        val token = PreferenceHelper.getToken(context)
        try {
            val codeToken = "Bearer " + token
            Log.d("AntesLlamada:", codeToken)
            val response = api.evaluateLevel(codeToken,evaluateLevel)
            return Result.success(response)
        } catch (e : Exception) {
            return Result.failure(e)
        }
    }

    suspend fun getRanking(context : Context): Result<List<Ranking>> {
        val token = PreferenceHelper.getToken(context)
        try {
            val codeToken = "Bearer " + token
            Log.d("AntesLlamada:", codeToken)
            val response = api.getRanking(codeToken)
            return Result.success(response)
        } catch (e : Exception) {
            return Result.failure(e)
        }
    }

    suspend fun getScore(context : Context): Result<Int> {
        val token = PreferenceHelper.getToken(context)
        try {
            val codeToken = "Bearer " + token
            Log.d("AntesLlamada:", codeToken)
            val response = api.getScore(codeToken)
            return Result.success(response)
        } catch (e : Exception) {
            return Result.failure(e)
        }
    }
}

