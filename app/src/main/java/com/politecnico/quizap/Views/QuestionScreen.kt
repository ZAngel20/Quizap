package com.politecnico.quizap.Views

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.politecnico.quizap.R
import com.politecnico.quizap.ViewModel.QuestionViewModel
import com.politecnico.quizap.data.Model.Answer
import com.politecnico.quizap.navigation.AppScreens
import com.politecnico.quizap.ui.theme.QuizapTheme
import kotlinx.coroutines.delay
import kotlin.math.ceil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionScreen(modifier: Modifier = Modifier, navController: NavController) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedAnswerId by remember { mutableStateOf(-1) }
    var correctAnswerId by remember { mutableStateOf(-1) }
    var showNextQuestion by remember { mutableStateOf(false) }
    var score by remember { mutableStateOf(0) }
    var aciertos by remember { mutableStateOf(0) }
    var isButton1Enabled by remember { mutableStateOf(true) }
    var isButton2Enabled by remember { mutableStateOf(true) }
    var isButton3Enabled by remember { mutableStateOf(true) }
    var isButton4Enabled by remember { mutableStateOf(true) }
    val questionViewModel = QuestionViewModel.getInstance()
    val viewModel = remember { questionViewModel }
    val questions by viewModel.listQuestion.observeAsState(initial = emptyList())
    val colors = listOf(Color(0xFF127489), Color(0xFF122689))
    val brush = Brush.verticalGradient(colors)
    var showMenu by remember { mutableStateOf(false) }
    var showResults by remember { mutableStateOf(false) }
    //var shuffledAnswers = questions[currentQuestionIndex].answers.shuffled()

    Column(
        modifier.fillMaxSize()
    ) {
        TopAppBar(
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF2BB5CA)),
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                }

            },
            navigationIcon = {
            },
            actions = {

                IconButton(onClick = {
                    showMenu = true
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Menú",
                        modifier = Modifier.fillMaxSize(),
                        tint = Color.Black
                    )
                }

            },

            )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .background(brush),
            contentAlignment = Alignment.Center
        ) {
            if (showMenu) {
                Dialog(

                    onDismissRequest = {  },
                    DialogProperties(
                        dismissOnBackPress = true,
                        dismissOnClickOutside = true
                    ),

                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(Color.Black.copy(alpha = 0.5f))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center)
                                .padding(horizontal = 4.dp)
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text =  stringResource(id = R.string.wantExit),
                                style = MaterialTheme.typography.displayMedium,
                                color = Color.White,
                                textAlign= TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(32.dp))
                            Spacer(modifier = Modifier.height(32.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 4.dp)
                            ) {
                                Button(
                                    onClick = {
                                        questionViewModel.shuffleAnswers()
                                        navController.navigate(AppScreens.QuestionScreen.route) {
                                        popUpTo(AppScreens.QuestionScreen.route) {
                                            inclusive = true
                                        }
                                    } },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF82189B)
                                    ),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center,
                                        text = stringResource(id = R.string.repeat),
                                        color = Color.White,
                                    )
                                }

                                Spacer(modifier = Modifier.width(16.dp))


                                Button(
                                    onClick = { navController.navigate(AppScreens.CategoriesScreen.route) {
                                        popUpTo(AppScreens.CategoriesScreen.route) {
                                            inclusive = true
                                        }
                                    } },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF82189B)
                                    ),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center,
                                        text = "Salir",
                                        color = Color.White,
                                    )
                                }

                            }
                            Button(
                                onClick = {
                                            showMenu = false
                                    },
                                modifier = Modifier
                                    .height(100.dp)
                                    .width(100.dp)
                                    .padding(vertical = 20.dp)
                                    .align(Alignment.CenterHorizontally),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF82189B)
                                ),
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Volver atrás"
                                )
                            }
                        }
                    }
                }
            }
            if (showResults) {
                ResultDialog(
                    totalQuestions=questions.size,
                    aciertos = aciertos,
                    score = score,
                    onDismiss = {
                        navController.navigate(AppScreens.CategoriesScreen.route) {
                            popUpTo(AppScreens.CategoriesScreen.route) {
                                inclusive = true
                            }
                        }
                    },
                    onRepeat = {
                        questionViewModel.shuffleAnswers()
                        navController.navigate(AppScreens.QuestionScreen.route) {
                            popUpTo(AppScreens.QuestionScreen.route) {
                                inclusive = true
                            }
                        }
                    },
                    onExit = {
                        navController.navigate(AppScreens.CategoriesScreen.route) {
                            popUpTo(AppScreens.CategoriesScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = questions[currentQuestionIndex].text,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(16.dp))


                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                ) {
                    AnswerButton(
                        answer = questions[currentQuestionIndex].answers[0],
                        isSelected = selectedAnswerId == questions[currentQuestionIndex].answers[0].id,
                        isCorrect = correctAnswerId == questions[currentQuestionIndex].answers[0].id,
                        isEnabled = isButton1Enabled,
                        onClick = {
                            selectedAnswerId = questions[currentQuestionIndex].answers[0].id
                            correctAnswerId = questions[currentQuestionIndex].idAnswer
                            if (selectedAnswerId == correctAnswerId) {
                                score += 100
                                aciertos++
                            } else { score -= 10}
                            isButton1Enabled = false
                            isButton2Enabled = false
                            isButton3Enabled = false
                            isButton4Enabled = false
                            showNextQuestion = true
                        }
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    AnswerButton(
                        answer = questions[currentQuestionIndex].answers[1],
                        isSelected = selectedAnswerId == questions[currentQuestionIndex].answers[1].id,
                        isCorrect = correctAnswerId == questions[currentQuestionIndex].answers[1].id,
                        isEnabled = isButton2Enabled,
                        onClick = {
                            selectedAnswerId = questions[currentQuestionIndex].answers[1].id
                            correctAnswerId = questions[currentQuestionIndex].idAnswer
                            if (selectedAnswerId == correctAnswerId) {
                                score += 100
                                aciertos++
                            } else { score -= 10}
                            isButton1Enabled = false
                            isButton2Enabled = false
                            isButton3Enabled = false
                            isButton4Enabled = false
                            showNextQuestion = true
                        }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                ) {
                    AnswerButton(
                        answer = questions[currentQuestionIndex].answers[2],
                        isSelected = selectedAnswerId == questions[currentQuestionIndex].answers[2].id,
                        isCorrect = correctAnswerId == questions[currentQuestionIndex].answers[2].id,
                        isEnabled = isButton3Enabled,
                        onClick = {
                            selectedAnswerId = questions[currentQuestionIndex].answers[2].id
                            correctAnswerId = questions[currentQuestionIndex].idAnswer
                            if (selectedAnswerId == correctAnswerId) {
                                score += 100
                                aciertos++
                            } else { score -= 10}
                            isButton1Enabled = false
                            isButton2Enabled = false
                            isButton3Enabled = false
                            isButton4Enabled = false
                            showNextQuestion = true
                        }
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    AnswerButton(
                        answer = questions[currentQuestionIndex].answers[3],
                        isSelected = selectedAnswerId == questions[currentQuestionIndex].answers[3].id,
                        isCorrect = correctAnswerId == questions[currentQuestionIndex].answers[3].id,
                        isEnabled = isButton4Enabled,
                        onClick = {
                            selectedAnswerId = questions[currentQuestionIndex].answers[3].id
                            correctAnswerId = questions[currentQuestionIndex].idAnswer
                            if (selectedAnswerId == correctAnswerId) {
                                score += 100
                                aciertos++
                            } else { score -= 10}
                            isButton1Enabled = false
                            isButton2Enabled = false
                            isButton3Enabled = false
                            isButton4Enabled = false
                            showNextQuestion = true
                        }
                    )
                }
            }

            LaunchedEffect(showNextQuestion) {
                if (showNextQuestion) {
                    delay(1500)
                    showNextQuestion = false
                    if (currentQuestionIndex < questions.size - 1) {
                        currentQuestionIndex++
                        isButton1Enabled = true
                        isButton2Enabled = true
                        isButton3Enabled = true
                        isButton4Enabled = true
                        selectedAnswerId = -1
                        correctAnswerId = -1
                    } else {
                        Log.d("Puntuacion:", score.toString())
                        showResults = true
                    }
                }
            }
        }
    }
}
@Composable
fun  AnswerButton(
    answer: Answer,
    isSelected: Boolean,
    isCorrect: Boolean,
    isEnabled: Boolean,
    onClick: () -> Unit
) {

    val brush = if (isSelected) {
        if (isCorrect) {
            val colors = listOf(Color(0xFF3CDA21), Color(0xFF3C9B18))
            Brush.verticalGradient(colors)
        } else {
            val colors = listOf(Color(0xFFDA2121), Color(0xFF9B1818))
            Brush.verticalGradient(colors)
        }
    } else {
        val colors = listOf(Color(0xFFB721DA), Color(0xFF82189B))
        Brush.verticalGradient(colors)

    }

    val animation = remember { Animatable(0f) }

    LaunchedEffect(isSelected) {
        if (isSelected) {
            animation.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 500)
            )
        } else {
            animation.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = 500)
            )
        }
    }

    Box(
        modifier = Modifier
            .size(160.dp, 160.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(brush = brush)
            .clickable(
                onClick = onClick,
                enabled = isEnabled
            ),

    ) {
        Text(
            text = answer.text,
            fontSize = 25.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(horizontal = 5.dp)
        )
    }

}
@Composable
fun ResultDialog(
    totalQuestions: Int,
    aciertos: Int,
    score   : Int,
    onDismiss: () -> Unit,
    onRepeat: () -> Unit,
    onExit: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    if (aciertos >= ceil(totalQuestions / 2.0)) {
                        Color(0xFF3C9B18).copy(alpha = 0.5f)
                    } else {
                        Color(0xFF9B1818).copy(alpha = 0.5f)
                    }
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .align(Alignment.Center)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = if (aciertos >= ceil(totalQuestions / 2.0)) stringResource(id = R.string.youWon) else stringResource(id = R.string.youLose),
                    style = MaterialTheme.typography.displayMedium,
                    color = Color.White,
                    textAlign= TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.youScore),
                    style = MaterialTheme.typography.displaySmall,
                    color = Color.White,
                    textAlign= TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "$score",
                    style = MaterialTheme.typography.displaySmall,
                    color = Color.White,
                    textAlign= TextAlign.Center
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Button(
                        onClick = onRepeat,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF82189B)
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(stringResource(id = R.string.repeat))
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = onExit,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF82189B)
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(stringResource(id = R.string.exit))
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun QuestionScreenPreview() {
    val navController = rememberNavController()
    QuizapTheme {
        QuestionScreen(navController = navController)
    }
}