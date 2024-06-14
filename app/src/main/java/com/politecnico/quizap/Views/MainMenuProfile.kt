package com.politecnico.quizap.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.politecnico.quizap.R
import com.politecnico.quizap.ViewModel.ProfileScreenViewModel
import com.politecnico.quizap.ViewModel.UserViewModel
import com.politecnico.quizap.data.Model.Profile
import com.politecnico.quizap.data.Model.Services.PreferenceHelper
import com.politecnico.quizap.navigation.AppScreens
import com.politecnico.quizap.ui.theme.QuizapTheme


@Composable
fun ProfileScreen(modifier: Modifier = Modifier,navController: NavHostController) {
    val colors = listOf(Color(0xFF127489), Color(0xFF122689))
    val brush = Brush.verticalGradient(colors)
    val context = LocalContext.current
    Column(
        modifier.fillMaxSize()
    ) {
        val tutorial = 7
        TopNavigation(modifier = modifier, navController = navController, tutorial = tutorial)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(brush),
            contentAlignment = Alignment.Center
        ) {
            val profileScreenViewModel = ProfileScreenViewModel.getInstance()
            val userViewModel = UserViewModel.getInstance()
            val uservM = remember { userViewModel }
            val profileviewModel = remember { profileScreenViewModel }
            profileScreenViewModel.setProfile(Profile(uservM.userName.value?: "-",uservM.email.value?: "-"))
            val name by profileviewModel.username.observeAsState(initial = "-")
            val email by profileviewModel.email.observeAsState(initial = "-")
            val score by profileviewModel.score.observeAsState(initial = 0)
            val ranking by profileviewModel.ranking.observeAsState(initial = null)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp, vertical = 30.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                // Nombre de usuario y correo electrónico
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.PName),
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )
                        Text(
                            text =  name,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )
                        Button(
                            onClick = {
                                navController.navigate(AppScreens.ChangeUser.route) {
                                popUpTo(AppScreens.ChangeUser.route) {
                                    inclusive = true
                                }
                            } },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF82189B)
                            )) {
                            Icon(imageVector = Icons.Filled.Edit, contentDescription = "Editar nombre de usuario", tint = Color.White)

                    }
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.PEmail),
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )
                        Text(
                            text = formatEmail(email),
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )
                    }
                }

                // Separador
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp),
                    color = Color.White
                )

                // Score y ranking
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.PScore),
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                    Text(
                        text = "${score}",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                }

                // Separador
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp),
                    color = Color.White
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.PRanking),
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                    Text(
                        text = if (ranking != null) "${ranking}" else stringResource(id = R.string.noRanking),
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.White
                    )
                }

                // Separador
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp),
                    color = Color.White
                )

                // Botones
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = { navController.navigate(AppScreens.ForgotPassword.route) {
                            popUpTo(AppScreens.ForgotPassword.route) {
                                inclusive = true
                            }
                        } },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF82189B)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent, shape = RoundedCornerShape(4.dp))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(stringResource(id = R.string.BTchangePass), color = Color.White)
                    }
                    Button(
                        onClick = {
                            PreferenceHelper.setToken(context,"")
                            val userViewModel = UserViewModel.getInstance()
                            userViewModel.logout()
                            navController.navigate(AppScreens.LoginScreen.route) {
                                popUpTo(AppScreens.LoginScreen.route) {
                                    inclusive = true
                                }
                            }
                                  },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF82189B)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent, shape = RoundedCornerShape(4.dp))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(stringResource(id = R.string.BTlogout), color = Color.White)
                    }
                }
            }
        }
        BottomNavigation(modifier = modifier, navController = navController, selectedTab = 2)
    }
}
fun formatEmail(email: String): String {
    if (email.equals("-")) {
        return "-"
    }
    val localPart = email.substringBefore("@")
    val domain = email.substringAfter("@")
    return "${localPart.take(3)}***@$domain"
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val navController = rememberNavController()
    QuizapTheme {
        ProfileScreen(navController = navController)
    }
}