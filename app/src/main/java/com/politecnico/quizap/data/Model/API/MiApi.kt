package com.politecnico.quizap.data.Model.API

import com.politecnico.quizap.data.Model.Answer
import com.politecnico.quizap.data.Model.Category
import com.politecnico.quizap.data.Model.CategoryDto
import com.politecnico.quizap.data.Model.Level
import com.politecnico.quizap.data.Model.LevelDto
import com.politecnico.quizap.data.Model.Question
import com.politecnico.quizap.data.Model.QuestionDto
import com.politecnico.quizap.data.Model.User
import com.politecnico.quizap.data.Model.UserAccessToken
import com.politecnico.quizap.data.Model.UserDto
import com.politecnico.quizap.data.Model.UserLogin
import com.politecnico.quizap.data.Model.UserResend
import com.politecnico.quizap.data.Model.UserToken
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MiAPI {
    companion object {
        val instance = Retrofit.Builder().baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(MoshiConverterFactory.create()).client(
            OkHttpClient.Builder().build())
            .build().create(MiAPI::class.java)
    }

    @POST("auth/signUp")
    suspend fun signUp(@Body user: UserDto): Response<Unit>
    @POST("auth/signUpCode")
    suspend fun signUpCode(@Body user: UserToken): Response<Unit>
    @POST("auth/signIn")
    suspend fun signIn(@Body user: UserLogin): UserAccessToken
    @POST("auth/resendActivationMail")
    suspend fun resendCode(@Body user: UserResend): Response<Unit>
    @GET("auth/getUser/test/")
    suspend fun getUser(@Header("Authorization") token: String): User

    // GAME INTERACTION
    @GET("/getCategories")
    suspend fun getCategories(): List<Category>
    @GET("/getLevels")
    suspend fun getLevels(@Body id : CategoryDto): List<Level>
    @GET("/getQuestions")
    suspend fun getQuestions(@Body id : LevelDto): List<Question>
    @GET("/getAnswers")
    suspend fun getAnswer(@Body id : QuestionDto): Answer
}
