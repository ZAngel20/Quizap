package com.politecnico.quizap.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.politecnico.quizap.R
import com.politecnico.quizap.navigation.AppScreens
import com.politecnico.quizap.ui.theme.QuizapTheme
@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController) {
    val colors = listOf(Color(0xFF127489), Color(0xFF122689))
    val brush = Brush.verticalGradient(colors)
    Column(
        modifier.fillMaxSize()
    ) {
        val tutorial = 7
        TopNavigation(modifier = modifier, navController = navController, tutorial = tutorial)
        Box(
            modifier = Modifier.fillMaxWidth()
                .weight(1f)
                .background(brush),
            contentAlignment = Alignment.Center
        ) {
            //Contenido
        }

        BottomNavigation(modifier = modifier, navController = navController, selectedTab = 3 )

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigation(modifier: Modifier = Modifier, navController: NavController, tutorial: Int) {

    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF2BB5CA)),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                /*Row(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_logo),
                        contentDescription = "Logo",
                        contentScale = ContentScale.
                    )
                }*/
            }

        },
        navigationIcon = {
            /*BadgedBox(badge = { Text(text = "200") },content = {IconButton(onClick = { /* Manejar clic del carrito */ }) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Carrito de compras"
                )

            }})
            if (tutorial > 6) {
                IconButton(onClick = { /* Manejar clic del carrito */ }) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Carrito de compras",
                        tint = Color.Black
                    )
                }
            }*/
        },
        actions = {
            // Derecha: Icono de las tres barras
            if (tutorial > 6) {
                IconButton(onClick = { /* Manejar clic del menú */ }) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Menú",
                        tint = Color.Black
                    )
                }
            }
        },

    )

}
@Composable
fun BottomNavigation(modifier: Modifier = Modifier, navController: NavController, selectedTab: Int) {
    NavigationBar(
        containerColor = Color(0xFFB85DE3),
        contentColor = Color(0xFFFF0000),
        modifier = modifier
    ) {
        NavigationBarItem(
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF6E2289),
                unselectedIconColor = Color(0xFF6E2289),
                selectedTextColor = Color.White,
                unselectedTextColor = Color.White,
                indicatorColor = Color(0xFFC46DED)
            ),
            icon = {
                Icon(
                    imageVector = Icons.Default.BarChart,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.bottom_navigation_ranking))
            },
            selected = selectedTab == 0,
            onClick = {
                navController.navigate(route = AppScreens.MainScreen.route)
            }
        )
        NavigationBarItem(
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF6E2289),
                unselectedIconColor = Color(0xFF6E2289),
                selectedTextColor = Color.White,
                unselectedTextColor = Color.White,
                indicatorColor = Color(0xFFC46DED)
            ),
            icon = {
                Icon(
                    imageVector = Icons.Default.MenuBook,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.bottom_navigation_categories))
            },
            selected = selectedTab == 1,
            onClick = {
                navController.navigate(route = AppScreens.CategoriesScreen.route)
            }
        )
        NavigationBarItem(
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF6E2289),
                unselectedIconColor = Color(0xFF6E2289),
                selectedTextColor = Color.White,
                unselectedTextColor = Color.White,
                indicatorColor = Color(0xFFC46DED)
            ),
            icon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.top_navigation_profile))
            },
            selected = selectedTab == 2,
            onClick = {
                navController.navigate(route = AppScreens.ProfileScreen.route)
            }
        )
    }
}
@Preview(showBackground = true)
@Composable
fun TopNavigationPreview() {
    val navController = rememberNavController()
    QuizapTheme {
        val tutorial = 1
        TopNavigation(navController = navController, tutorial = tutorial)
    }
}
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    QuizapTheme {
        HomeScreen(navController = navController)
    }
}
@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    val navController = rememberNavController()
    QuizapTheme {
        BottomNavigation(navController = navController, selectedTab = 2)
    }
}