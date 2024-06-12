package com.politecnico.quizap.Tutorial

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.politecnico.quizap.R
import com.politecnico.quizap.Views.TopNavigation
import com.politecnico.quizap.data.Model.Services.PreferenceHelper
import com.politecnico.quizap.navigation.AppScreens
import com.politecnico.quizap.ui.theme.QuizapTheme

@Composable
fun TutoScreen(modifier: Modifier = Modifier, navController: NavController) {

    val context = LocalContext.current
    var tutorialStatus by remember { mutableStateOf(PreferenceHelper.getTutorialStatus(context)) }
    var tutorial by remember { mutableStateOf(1) }
    if (tutorialStatus == 7) {
        navController.navigate(route = AppScreens.CategoriesScreen.route)
    } else {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopNavigation(modifier = modifier, navController = navController, tutorial = tutorial)
            ProvideTextStyle(TextStyle(color = Color.White)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color(0xFF127489),
                                    Color(0xFF6E2289)
                                )
                            )
                        ),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {


                        Text(
                            modifier = Modifier.padding(top = 20.dp, start = 10.dp, end = 10.dp),
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.ExtraBold,
                            text = stringResource(id = getResourceIdForTutorialTitle(tutorial))
                        )
                        Text(
                            modifier = Modifier.padding(top = 20.dp, start = 10.dp, end = 10.dp),
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.ExtraBold,
                            text = stringResource(id = getResourceIdForTutorialContent(tutorial))
                        )
                        Text(
                            modifier = Modifier.padding(top = 20.dp, start = 10.dp, end = 10.dp),
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.ExtraBold,
                            text = stringResource(id = getResourceIdForTutorialEnd(tutorial))
                        )
                    }
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 50.dp)
                    ) {
                        if (tutorial == 6) {
                            Button(
                                onClick = {
                                    navController.navigate(route = AppScreens.CategoriesScreen.route)
                                }
                            ) {
                                Text(text = stringResource(id = R.string.play))
                            }
                        } else {
                            FloatingActionButton(
                                onClick = {
                                    if (tutorial > 1) {
                                        tutorial--
                                        PreferenceHelper.setTutorialStatus(context, tutorial)
                                    }
                                },
                                content = {
                                    Icon(
                                        Icons.Default.ArrowBack,
                                        contentDescription = "Atrás"
                                    )
                                }
                            )
                            Spacer(modifier = Modifier.width(30.dp))
                            if (tutorial == 5) {
                                Button(
                                    onClick = {
                                        tutorial++
                                        PreferenceHelper.setTutorialStatus(context, tutorial)
                                    }
                                ) {
                                    Text(text = stringResource(id = R.string.howtoplay))
                                }
                            }
                            if (tutorial < 5) {

                                FloatingActionButton(
                                    onClick = {
                                        tutorial++
                                        PreferenceHelper.setTutorialStatus(context, tutorial)
                                    },
                                    content = {
                                        Icon(
                                            Icons.Default.ArrowForward,
                                            contentDescription = "Siguiente"
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun getResourceIdForTutorialTitle(tutorial: Int): Int {
    return when (tutorial) {
        1 -> R.string.tutorial_1_title
        2 -> R.string.tutorial_2_title
        3 -> R.string.tutorial_3_title
        4 -> R.string.tutorial_4_title
        5 -> R.string.tutorial_5_title
        6 -> R.string.tutorial_6_title
        else -> throw IllegalArgumentException("No hay título definido para el tutorial $tutorial")
    }
}

@Composable
fun getResourceIdForTutorialContent(tutorial: Int): Int {
    return when (tutorial) {
        1 -> R.string.tutorial_1_content
        2 -> R.string.tutorial_2_content
        3 -> R.string.tutorial_3_content
        4 -> R.string.tutorial_4_content
        5 -> R.string.tutorial_5_content
        6 -> R.string.tutorial_6_content
        else -> throw IllegalArgumentException("No hay contenido definido para el tutorial $tutorial")
    }
}
@Composable
fun getResourceIdForTutorialEnd(tutorial: Int): Int {
    return when (tutorial) {
        1 -> R.string.tutorial_1_end
        2 -> R.string.tutorial_2_end
        3 -> R.string.tutorial_3_end
        4 -> R.string.tutorial_4_end
        5 -> R.string.tutorial_5_end
        6 -> R.string.tutorial_6_end
        else -> throw IllegalArgumentException("No hay final definido para el tutorial $tutorial")
    }
}
@Preview(showBackground = true)
@Composable
fun TutoScreenPreview() {
    val navController = rememberNavController()
    QuizapTheme {
        TutoScreen(navController = navController)
    }
}