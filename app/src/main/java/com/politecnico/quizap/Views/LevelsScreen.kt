package com.politecnico.quizap.Views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.politecnico.quizap.R
import com.politecnico.quizap.ViewModel.LevelViewModel
import com.politecnico.quizap.ViewModel.QuestionViewModel
import com.politecnico.quizap.data.Model.API.MiAPI
import com.politecnico.quizap.data.Model.Answer
import com.politecnico.quizap.data.Model.GameRepository
import com.politecnico.quizap.data.Model.Level
import com.politecnico.quizap.data.Model.LevelDto
import com.politecnico.quizap.data.Model.PreQuestion
import com.politecnico.quizap.data.Model.Question
import com.politecnico.quizap.data.Model.QuestionDto
import com.politecnico.quizap.navigation.AppScreens
import com.politecnico.quizap.ui.theme.QuizapTheme
import kotlinx.coroutines.launch

@Composable
fun LevelScreen(modifier: Modifier = Modifier, navController: NavController) {
    val levelViewModel = LevelViewModel.getInstance()
    val viewModel = remember { levelViewModel}
    val levels by viewModel.listLevel.observeAsState(initial = emptyList())
    val colors = listOf(Color(0xFF127489), Color(0xFF122689))
    val brush = Brush.verticalGradient(colors)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Column(
        modifier.fillMaxSize()
    ) {
        val tutorial = 7
        TopNavigation(modifier = modifier, navController = navController, tutorial = tutorial)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .background(brush),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.selectLevel),
                    style = MaterialTheme.typography.displayLarge,
                    color = Color.White,
                )
                for (i in levels.indices step 3) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),


                    ) {
                        for (j in 0..2) {
                            val level = levels[i + j]
                            LevelCard(level = level, onClick = {
                                val questionViewModel = QuestionViewModel.getInstance()
                                val gameRepository: GameRepository by lazy { GameRepository(MiAPI.instance) }
                                scope.launch {
                                    val result : Result<List<PreQuestion>> = gameRepository.getQuestions(context,
                                        LevelDto(level.id)
                                    )
                                    var listaPreQuestion : List<PreQuestion> = emptyList()
                                    val completeQuestionsList: MutableList<Question> = mutableListOf()
                                    try {
                                        Log.d("Resultado Bueno:",result.toString())
                                        val listQuestion: List<PreQuestion>? = result.getOrNull()
                                        if (listQuestion != null) {
                                            listaPreQuestion = listQuestion
                                        }
                                    } catch (e : Exception) {
                                        Log.d("Resultado Malo:",result.toString())
                                        listaPreQuestion = emptyList()
                                    }
                                    listaPreQuestion.forEach { question ->
                                        val result : Result<List<Answer>> = gameRepository.getAnswers(context,
                                            QuestionDto(question.id)
                                        )
                                        var listaAnswer : List<Answer> = emptyList()
                                        try {
                                            Log.d("Resultado Bueno:",result.toString())
                                            val listAnswer: List<Answer>? = result.getOrNull()
                                            if (listAnswer != null) {
                                                listaAnswer = listAnswer
                                            }
                                        } catch (e : Exception) {
                                            Log.d("Resultado Malo:",result.toString())
                                            listaAnswer = emptyList()
                                        }
                                        completeQuestionsList.add(Question(question.id,question.idAnswer,question.text,question.index,listaAnswer))
                                        questionViewModel.shuffleAnswers()
                                        questionViewModel.shuffleAnswers()
                                        questionViewModel.shuffleAnswers()
                                        questionViewModel.shuffleAnswers()
                                    }

                                    questionViewModel.setQuestions(completeQuestionsList)
                                    navController.navigate("question_screen/${level.id}")
                                }
                            })
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    navController.navigate(AppScreens.CategoriesScreen.route) {
                        popUpTo(AppScreens.CategoriesScreen.route) {
                            inclusive = true
                        }
                    }},
                 colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF82189B)
                ),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height(100.dp)
                    .width(100.dp)

                    .padding(vertical = 20.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Volver atrás"
                )
            }
        }
    }
}

@Composable
fun LevelCard(level: Level, onClick: () -> Unit) {
    val colors = listOf(Color(0xFFB721DA), Color(0xFF82189B))
    val brush = Brush.verticalGradient(colors)
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(brush, shape = RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
    ) {
        Text(
            text = level.name,
            fontSize = 30.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LevelScreenPreview() {
    val navController = rememberNavController()
    QuizapTheme {
        LevelScreen(navController = navController)
    }
}