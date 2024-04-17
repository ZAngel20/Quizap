package com.politecnico.quizap.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen: AppScreens("Splash")
    object TutorialScreen: AppScreens("Tutorial")
    object MainScreen: AppScreens("main_screen")
    object LoginScreen: AppScreens("login_screen")
    object RegistrationScreen : AppScreens("registration_screen")

}
