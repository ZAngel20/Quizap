package com.politecnico.quizap.Views

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.politecnico.quizap.R
import com.politecnico.quizap.ViewModel.UserViewModel
import com.politecnico.quizap.data.Model.API.MiAPI
import com.politecnico.quizap.data.Model.Services.PreferenceHelper
import com.politecnico.quizap.data.Model.User
import com.politecnico.quizap.data.Model.UserRepository
import com.politecnico.quizap.navigation.AppScreens
import com.politecnico.quizap.ui.theme.QuizapTheme
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavHostController) {

    val context = LocalContext.current
    val token = PreferenceHelper.getToken(context)
    val colors = listOf(Color(0xFF0CADC1), Color(0xFFB85DE3))
    val brush = Brush.verticalGradient(colors)
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleScope = lifecycleOwner.lifecycleScope
    val userRepository: UserRepository by lazy { UserRepository(MiAPI.instance) }
    Log.d("Token:",token + " igual a " + PreferenceHelper.getToken(context))

    if (token.isNotEmpty()) {
        val userViewModel = UserViewModel.getInstance()

        lifecycleScope.launch {
            val result : Result<User> = userRepository.getUser(context)
            try {

                    val user : User? = result.getOrNull()
                    if (user != null) {
                        userViewModel.setUser(user)
                        Log.d("Resultado:",result.toString())
                        Log.d("Resultado:",user.id)
                        Log.d("Resultado:",user.userName)
                        Log.d("Resultado:",user.email)
                        navController.navigate(route = AppScreens.CategoriesScreen.route)
                        } else {
                            Log.d("Resultado:",result.toString())
                        navController.navigate(route = AppScreens.LoginScreen.route)
                        }
            } catch (e: Exception) {
                Log.d("Resultado:",result.toString())
                navController.navigate(route = AppScreens.LoginScreen.route)
            }
        }
    } else {
        Log.d("Resultado:","Token Vacio")
        navController.navigate(route = AppScreens.LoginScreen.route)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Logo",
            modifier = Modifier.fillMaxWidth(0.5f).fillMaxHeight(0.5f),
            contentScale = ContentScale.Fit
        )
    }
}
@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    val navController = rememberNavController()
    QuizapTheme {
        SplashScreen(navController = navController)
    }
}