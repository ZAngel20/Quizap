package com.politecnico.quizap.Views

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.politecnico.quizap.ViewModel.QuestionViewModel
import com.politecnico.quizap.data.Model.Answer
import com.politecnico.quizap.ui.theme.QuizapTheme
import kotlinx.coroutines.delay

@Composable
fun QuestionScreen(modifier: Modifier = Modifier, navController: NavController) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedAnswerId by remember { mutableStateOf(-1) }
    var correctAnswerId by remember { mutableStateOf(-1) }
    var showNextQuestion by remember { mutableStateOf(false) }
    val questionViewModel = QuestionViewModel.getInstance()
    val viewModel = remember { questionViewModel}
    val questions by viewModel.listQuestion.observeAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = questions[currentQuestionIndex].text,
            style = MaterialTheme.typography.displayLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            AnswerButton(
                answer = questions[currentQuestionIndex].answers[0],
                isSelected = selectedAnswerId == questions[currentQuestionIndex].answers[0].id,
                isCorrect = correctAnswerId == questions[currentQuestionIndex].answers[0].id,
                onClick = {
                    selectedAnswerId = questions[currentQuestionIndex].answers[0].id
                    correctAnswerId = questions[currentQuestionIndex].correctAnswer
                    showNextQuestion = true
                }
            )

            Spacer(modifier = Modifier.width(16.dp))

            AnswerButton(
                answer = questions[currentQuestionIndex].answers[1],
                isSelected = selectedAnswerId == questions[currentQuestionIndex].answers[1].id,
                isCorrect = correctAnswerId == questions[currentQuestionIndex].answers[1].id,
                onClick = {
                    selectedAnswerId = questions[currentQuestionIndex].answers[1].id
                    correctAnswerId = questions[currentQuestionIndex].correctAnswer
                    showNextQuestion = true
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            AnswerButton(
                answer = questions[currentQuestionIndex].answers[2],
                isSelected = selectedAnswerId == questions[currentQuestionIndex].answers[2].id,
                isCorrect = correctAnswerId == questions[currentQuestionIndex].answers[2].id,
                onClick = {
                    selectedAnswerId = questions[currentQuestionIndex].answers[2].id
                    correctAnswerId = questions[currentQuestionIndex].correctAnswer
                    showNextQuestion = true
                }
            )

            Spacer(modifier = Modifier.width(16.dp))

            AnswerButton(
                answer = questions[currentQuestionIndex].answers[3],
                isSelected = selectedAnswerId == questions[currentQuestionIndex].answers[3].id,
                isCorrect = correctAnswerId == questions[currentQuestionIndex].answers[3].id,
                onClick = {
                    selectedAnswerId = questions[currentQuestionIndex].answers[3].id
                    correctAnswerId = questions[currentQuestionIndex].correctAnswer
                    showNextQuestion = true
                }
            )
        }
    }

    LaunchedEffect(showNextQuestion) {
        if (showNextQuestion) {
            delay(3000)
            showNextQuestion = false
            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
            } else {
                navController.navigate("endScreen")
            }
        }
    }
}

@Composable
fun  AnswerButton(
    answer: Answer,
    isSelected: Boolean,
    isCorrect: Boolean,
    onClick: () -> Unit
) {
    val color = if (isSelected) {
        if (isCorrect) Color.Green else Color.Red
    } else {
        Color.Gray
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
            .size(120.dp, 40.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .clickable(onClick = onClick),
    ) {
        Text(
            text = answer.text,
            style = MaterialTheme.typography.titleLarge
        )
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