package com.politecnico.quizap.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen: AppScreens("Splash")
    object SecondScreen: AppScreens("second_screen")
    object LoginScreen: AppScreens("login_screen")
    object RegistrationScreen : AppScreens("registration_screen")

}
