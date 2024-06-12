package com.politecnico.quizap.data.Model.Services

import android.content.Context
import android.net.ConnectivityManager
import java.io.IOException
import java.net.InetAddress

fun hasInternetConnection(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
        try {
            val ipAddress = InetAddress.getByName("www.google.com")
            return ipAddress.isReachable(1000) // timeout de 1 segundo
        } catch (e: IOException) {
            return false
        }
    } else {
        return false
    }
}