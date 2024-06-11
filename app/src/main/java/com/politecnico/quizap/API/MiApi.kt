package com.politecnico.quizap.API

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST

interface MiAPI {
    companion object {
        val instance = Retrofit.Builder().baseUrl("http://localhost:3000/").addConverterFactory(MoshiConverterFactory.create()).client(
            OkHttpClient.Builder().build()).build().create(MiAPI::class.java)
        }

        @POST("auth/signUp")
        suspend fun singUp()
        @POST("auth/signUpCode")
        suspend fun singUpCode()
        @POST("auth/signIn")
        suspend fun singIn()
        @POST("auth/resendActivationMail")
        suspend fun resendCode()
        @GET("auth/getUser/test")
        suspend fun getUser(): User
    }
}