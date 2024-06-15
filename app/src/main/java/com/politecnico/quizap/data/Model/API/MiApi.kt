package com.politecnico.quizap.data.Model.API

import com.politecnico.quizap.data.Model.Answer
import com.politecnico.quizap.data.Model.Category
import com.politecnico.quizap.data.Model.EvaluateLevel
import com.politecnico.quizap.data.Model.Level
import com.politecnico.quizap.data.Model.PreQuestion
import com.politecnico.quizap.data.Model.Ranking
import com.politecnico.quizap.data.Model.User
import com.politecnico.quizap.data.Model.UserAccessToken
import com.politecnico.quizap.data.Model.UserDto
import com.politecnico.quizap.data.Model.UserLogin
import com.politecnico.quizap.data.Model.UserName
import com.politecnico.quizap.data.Model.UserPass
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
import retrofit2.http.PUT
import retrofit2.http.Path

interface MiAPI {
    companion object {
        val instance = Retrofit.Builder().baseUrl("https://pruebaparaangel123123.publicvm.com:2001/")
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
    @GET("auth")
    suspend fun getUser(@Header("Authorization") token: String): User
    @POST("auth/requestPasswdChange")
    suspend fun requestChangePass(@Header("Authorization") token: String, @Body user : UserResend ): Response<Unit>
    @POST("auth/changePasswd")
    suspend fun changePass(@Header("Authorization") token: String, @Body  userPass: UserPass ): Response<Unit>
    @PUT("auth")
    suspend fun changeUser(@Header("Authorization") token: String, @Body user : UserName): Response<Unit>
    // GAME INTERACTION
    @GET("/category")
    suspend fun getCategories(@Header("Authorization") token: String): List<Category>
    @GET("/level/{id}")
    suspend fun getLevels(@Header("Authorization") token: String, @Path("id") id : Int): List<Level>
    @GET("/question/{idLevel}")
    suspend fun getQuestions(@Header("Authorization") token: String, @Path("idLevel") idLevel : Int): List<PreQuestion>
    @GET("/answer/{idQuestion}")
    suspend fun getAnswers(@Header("Authorization") token: String, @Path("idQuestion") idQuestion : Int): List<Answer>
    @GET("/answer/correct/{idQuestion}")
    suspend fun getCorrectAnswer(@Header("Authorization") token: String, @Path("idQuestion") idQuestion : Int): Answer
    @GET("/level/evaluateLevel")
    suspend fun evaluateLevel(@Header("Authorization") token: String, @Body evaluateLevel : EvaluateLevel): Int
    @GET("/ranking")
    suspend fun getRanking(@Header("Authorization") token: String): List<Ranking>
    @GET("/ranking/score")
    suspend fun getScore(@Header("Authorization") token: String): Int
}
