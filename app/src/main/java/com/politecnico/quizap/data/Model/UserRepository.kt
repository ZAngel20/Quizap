package com.politecnico.quizap.data.Model

import android.content.Context
import android.util.Log
import com.politecnico.quizap.data.Model.API.MiAPI
import com.politecnico.quizap.data.Model.Services.PreferenceHelper

class UserRepository(
    private val api: MiAPI
) {
    suspend fun signIn(user: UserLogin): Result<UserAccessToken> {
        try {
            Log.d("AntesLlamada:" ,user.email)
            val response = api.signIn(user)
            Log.d("DespuesLlamada:" ,response.toString())
            return Result.success(response)
        } catch (e : Exception) {
            Log.d("ErrorLlamada:" ,e.message?: "No hay Texto de Error")
            val errorMessage = e.message?: "Error desconocido"
            return Result.failure(RuntimeException(errorMessage))
        }
    }
    suspend fun signUpCode(user: UserToken): MyMessage {
        Log.d ("Antes Llamada",user.token)
        val response = api.signUpCode(user)
        Log.d ("Despues Llamada",response.toString())
        if (response.isSuccessful) {
            Log.d ("Despues Succes",response.toString())
            return MyMessage(0)
        } else {
            val errorCode = response.code()
            val errorMessage = response.errorBody()?.string()
            Log.d("ErrorLlamada:", "Error $errorCode: $errorMessage")
            return MyMessage(errorCode)
        }
    }

    suspend fun resendCode(user: UserResend) : MyMessage {
        Log.d ("Antes Llamada",user.email)
        val response = api.resendCode(user)
        Log.d ("Despues Llamada",response.toString())
        if (response.isSuccessful) {
            Log.d ("Despues Succes",response.toString())
            return MyMessage(0)
        } else {
            val errorCode = response.code()
            val errorMessage = response.errorBody()?.string()
            Log.d("ErrorLlamada:", "Error $errorCode: $errorMessage")
            return MyMessage(errorCode)
        }
    }
   suspend fun signUp(user: UserDto) : MyMessage {
       Log.d ("Antes Llamada",user.email)
       val response = api.signUp(user)
       Log.d ("Despues Llamada",response.toString())
       if (response.isSuccessful) {
           Log.d ("Despues Succes",response.toString())
           return MyMessage(0)
       } else {
           val errorCode = response.code()
           val errorMessage = response.errorBody()?.string()
           Log.d("ErrorLlamada:", "Error $errorCode: $errorMessage")
           return MyMessage(errorCode)
       }
   }
   suspend fun getUser(context: Context): Result<User> {
       val token = PreferenceHelper.getToken(context)
       try {
           val codeToken = "Bearer " + token
           Log.d("AntesLlamada:", codeToken)
           val response = api.getUser(codeToken)
           return Result.success(response)
       } catch (e : Exception) {
           return Result.failure(e)
       }
   }
}

