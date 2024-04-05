package com.politecnico.quizap.navigation

import android.app.Activity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.politecnico.quizap.SplashScreen
import com.politecnico.quizap.navigation.AppScreens


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val activity = LocalContext.current as? Activity
    val windowSizeClass = calculateWindowSizeClass(activity!!)

    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
        composable(route = AppScreens.LoginScreen.route) {
            SplashScreen(navController = navController)
        }

        composable(route = AppScreens.SecondScreen.route) {
            SecondScreen(navController = navController)
        }
        
        composable(route = AppScreens.FirstScreen.route) {
            MySootheApp(windowSize = windowSizeClass, navController = navController)
        }

        composable(route = AppScreens.RegistrationScreen.route) {
            RegistrationScreen(navController = navController)
        }
        composable(route = AppScreens.ReservaForm.route) {
            ReservationForm(navController = navController)
        }
        composable(route = AppScreens.CarouselScreen.route) {
            CarouselScreen(navController = navController)
        }
        composable(
                route = AppScreensReserva.ReservaList.route
            ){
            ReservationListScreen(
                    navigateToBookDetail = {
                        navController.navigate(AppScreensReserva.ReservaDetail.route)
                    },
                    isRefreshing = false,
                    refreshData = {},
                navController
                )
            }

        composable(
                route = AppScreensReserva.ReservaDetail.route
            ){

                val viewModel: ReservationDetailViewModel = viewModel()
                val state = viewModel.state.value

                ReservationDetailScreen(
                    state = state,
                    addNewReservation = viewModel::addNewReservation,
                    navController
                )
            }
        }
    }
