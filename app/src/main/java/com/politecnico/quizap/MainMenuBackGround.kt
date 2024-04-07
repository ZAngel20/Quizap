package com.politecnico.quizap

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.StackedBarChart
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.politecnico.quizap.Authentication.RegistrationScreen
import com.politecnico.quizap.navigation.AppScreens
import com.politecnico.quizap.ui.theme.QuizapTheme
@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController) {
    val colors = listOf(Color(0xFF127489), Color(0xFF6E2289))
    val brush = Brush.verticalGradient(colors)
    Column(
        modifier.fillMaxSize()
    ) {
        TopNavigation(modifier = modifier, navController = navController)
        Box(
            modifier = Modifier.fillMaxWidth()
                .weight(1f)
                .background(brush),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Contenido en el medio")
        }

        BottomNavigation(modifier = modifier, navController = navController)

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigation(modifier: Modifier = Modifier, navController: NavController) {

    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF2BB5CA)),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProvideTextStyle(TextStyle(color = Color.White)) {
                Text(text = "200")}
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

            }})*/
            IconButton(onClick = { /* Manejar clic del carrito */ }) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Carrito de compras"
                )
            }
        },
        actions = {
            // Derecha: Icono de las tres barras
            IconButton(onClick = { /* Manejar clic del menú */ }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menú"
                )
            }
        },

    )

}
@Composable
fun BottomNavigation(modifier: Modifier = Modifier, navController: NavController) {
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
            selected = false,
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
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.bottom_navigation_home))
            },
            selected = true,
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
            selected = false,
            onClick = {
                //navController.navigate(route = AppScreens.ReservaForm.route)
            }
        )
    }
}
@Preview(showBackground = true)
@Composable
fun TopNavigationPreview() {
    val navController = rememberNavController()
    QuizapTheme {
        TopNavigation(navController = navController)
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
        BottomNavigation(navController = navController)
    }
}