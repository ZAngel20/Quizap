package com.politecnico.quizap.navigation

import android.app.Activity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codelab.basiclayouts.Authentication.LoginScreen
import com.politecnico.quizap.Authentication.RegistrationScreen
import com.politecnico.quizap.CategoriesScreen
import com.politecnico.quizap.HomeScreen
import com.politecnico.quizap.ProfileScreen
import com.politecnico.quizap.Tutorial.TutoScreen


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val activity = LocalContext.current as? Activity
    val windowSizeClass = calculateWindowSizeClass(activity!!)

    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
            composable(route = AppScreens.LoginScreen.route) {
                LoginScreen(navController = navController)
            }
            composable(route = AppScreens.ForgotPassword.route) {
                LoginScreen(navController = navController)
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
        }
    }
