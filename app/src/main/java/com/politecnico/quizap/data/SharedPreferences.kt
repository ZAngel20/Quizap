package com.politecnico.quizap.data

import android.content.Context

object PreferenceHelper {

    private const val PREFERENCE_FILE_KEY = "com.politecnico.quizap.PREFERENCE_FILE_KEY"
    private const val TUTORIAL_KEY = "TUTORIAL_KEY"
    private const val TOKEN_KEY = "TOKEN_KEY"

    fun setTutorialStatus(context: Context, status: Int) {
        val sharedPref = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt(TUTORIAL_KEY, status)
            apply()
        }
    }

    fun getTutorialStatus(context: Context): Int {
        val sharedPref = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)
        return sharedPref.getInt(TUTORIAL_KEY, 1)  // 1 es el valor predeterminado si no se encuentra nada
    }

    fun setToken(context: Context, token: String) {
        val sharedPref = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(TOKEN_KEY, token)
            apply()
        }
    }

    fun getToken(context: Context): String {
        val sharedPref = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)
        return sharedPref.getString(TOKEN_KEY, "") ?: ""  // "" es el valor predeterminado
    }
}