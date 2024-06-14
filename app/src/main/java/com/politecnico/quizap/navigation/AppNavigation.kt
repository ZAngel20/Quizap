package com.politecnico.quizap.navigation

import android.app.Activity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.codelab.basiclayouts.Authentication.ChangeUser
import com.codelab.basiclayouts.Authentication.ForgotPassword
import com.politecnico.quizap.Authentication.LoginScreen
import com.politecnico.quizap.Authentication.RegistrationScreen
import com.politecnico.quizap.Authentication.VerificacionTokenScreen
import com.politecnico.quizap.Tutorial.TutoScreen
import com.politecnico.quizap.Views.CategoriesScreen
import com.politecnico.quizap.Views.HomeScreen
import com.politecnico.quizap.Views.LevelScreen
import com.politecnico.quizap.Views.ProfileScreen
import com.politecnico.quizap.Views.QuestionScreen
import com.politecnico.quizap.Views.SplashScreen


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val activity = LocalContext.current as? Activity
    val windowSizeClass = calculateWindowSizeClass(activity!!)

    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route) {
            composable(route = AppScreens.SplashScreen.route) {
                SplashScreen(navController = navController)
            }
            composable(route = AppScreens.LoginScreen.route) {
                LoginScreen(navController = navController)
            }
            composable(route = AppScreens.ForgotPassword.route) {
                ForgotPassword(navController = navController)
            }
            composable(route = AppScreens.ChangeUser.route) {
                ChangeUser(navController = navController)
            }
            composable(route = AppScreens.TutorialScreen.route) {
                TutoScreen(navController = navController)
            }
            composable(route = AppScreens.RegistrationScreen.route) {
                RegistrationScreen(navController = navController)
            }
            composable(route = AppScreens.MainScreen.route) {
                HomeScreen(navController = navController)
            }
            composable(route = AppScreens.CategoriesScreen.route) {
                CategoriesScreen(navController = navController)
            }
            composable(route = AppScreens.ProfileScreen.route) {
                ProfileScreen(navController = navController)
            }
            composable(route = "verifyToken/{email}",
                arguments = listOf(
                    navArgument("email") {
                        type = NavType.StringType
                    }
                )) {
                val email = it.arguments?.getString("email") ?: ""
                VerificacionTokenScreen(navController = navController, email = email)
            }
            composable(route = AppScreens.LevelScreen.route) {
                LevelScreen(navController = navController)
            }
        composable(route = AppScreens.QuestionScreen.route) {
            QuestionScreen(navController = navController)
        }
        }
    }
