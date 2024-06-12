package com.politecnico.quizap.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen: AppScreens("Splash")
    object TutorialScreen: AppScreens("Tutorial")
    object MainScreen: AppScreens("main_screen")
    object LoginScreen: AppScreens("login_screen")
    object ForgotPassword: AppScreens("forgot_screen")
    object RegistrationScreen : AppScreens("registration_screen")
    object ProfileScreen : AppScreens("profile_screen")
    object CategoriesScreen : AppScreens("category_screen")
    object LevelScreen : AppScreens ("level_screen")
    object QuestionScreen : AppScreens ("question_screen")
}
