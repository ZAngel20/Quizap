package com.politecnico.quizap

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.politecnico.quizap.ViewModel.ProfileScreenViewModel
import com.politecnico.quizap.ui.theme.QuizapTheme


@Composable
fun ProfileScreen(modifier: Modifier = Modifier,navController: NavHostController) {
    val colors = listOf(Color(0xFF127489), Color(0xFF122689))
    val brush = Brush.verticalGradient(colors)
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
            val viewModel = remember { ProfileScreenViewModel() }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp, vertical = 30.dp),
                verticalArrangement = Arrangement.spacedBy(40.dp)
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
                            text = "Nombre:",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )
                        Text(
                            text = viewModel.username,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )
                        Button(
                            onClick = { /* editar nombre de usuario */ },
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
                            text = "Correo:",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )
                        Text(
                            text = formatEmail(viewModel.email),
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )
                        Button(onClick = { /* editar nombre de usuario */ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF82189B)
                            )) {
                            Icon(imageVector = Icons.Filled.Edit, contentDescription = "Editar nombre de usuario", tint = Color.White)

                        }
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
                        text = "Score:",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                    Text(
                        text = "${viewModel.score}",
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
                        text = "Ranking:",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                    Text(
                        text = if (viewModel.ranking!= null) "Ranking: ${viewModel.ranking}" else "No estás en el ranking",
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
                        onClick = { /* cambiar contraseña */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF82189B)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent, shape = RoundedCornerShape(4.dp))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text("Cambiar contraseña", color = Color.White)
                    }
                    Button(
                        onClick = { /* desloguearse */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF82189B)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent, shape = RoundedCornerShape(4.dp))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text("Cerrar Sesión", color = Color.White)
                    }
                }
            }
        }



        BottomNavigation(modifier = modifier, navController = navController, selectedTab = 2)

    }
}
fun formatEmail(email: String): String {
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