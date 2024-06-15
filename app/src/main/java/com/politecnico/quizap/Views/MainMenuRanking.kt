package com.politecnico.quizap.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.politecnico.quizap.ViewModel.RankingScreenViewModel
import com.politecnico.quizap.data.Model.Ranking
import com.politecnico.quizap.ui.theme.QuizapTheme


@Composable
fun RankingScreen(modifier: Modifier = Modifier, navController: NavController) {
    val colors = listOf(Color(0xFF127489), Color(0xFF122689))
    val brush = Brush.verticalGradient(colors)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val listRanking = remember {mutableStateListOf<Ranking>()}

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
            val rankingScreenViewModel = RankingScreenViewModel.getInstance()
            val viewModel = remember { rankingScreenViewModel}
            val rankings by viewModel.listRanking.observeAsState(initial = emptyList())
            rankingScreenViewModel.setRanking(context)
            RankingList(rankings)
            }
            BottomNavigation(modifier = modifier, navController = navController, selectedTab = 1)

        }
    }
@Composable
fun RankingList(
    rankings: List<Ranking>
) {
    LazyColumn {
        items(rankings) {ranking ->
            RankingItem(
                position = rankings.indexOf(ranking) + 1,
                userName = ranking.user.userName,
                score = ranking.score
            )
        }

    }
}
@Composable
fun RankingItem(
    position: Int,
    userName: String,
    score: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Text(
            text = position.toString(),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(end = 16.dp)
        )
        Text(
            text = userName,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = score.toString(),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RankingScreenPreview() {
    val navController = rememberNavController()
    QuizapTheme {
        RankingScreen(navController = navController)
    }
}
