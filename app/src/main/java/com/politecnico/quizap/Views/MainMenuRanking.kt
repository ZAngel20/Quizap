package com.politecnico.quizap.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.politecnico.quizap.R
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
            contentAlignment = Alignment.TopCenter
        ) {
            val rankingScreenViewModel = RankingScreenViewModel.getInstance()
            val viewModel = remember { rankingScreenViewModel}
            val rankings by viewModel.listRanking.observeAsState(initial = emptyList())
            rankingScreenViewModel.setRanking(context)
            //RankingList(rankings)
            LazyColumn {
                items(rankings) { ranking ->
                    RankingRow(ranking, rankings.indexOf(ranking) + 1)
                }
            }
            }
            BottomNavigation(modifier = modifier, navController = navController, selectedTab = 0)

        }
    }

@Composable
fun RankingRow(rankingItem: Ranking, index:Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(2.dp)
            .background(Color(0xFF27B1CA).copy(0.15f)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column (verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(0.5f)) {
            if (index == 1) {
                Image(painter = painterResource(R.drawable.crown), contentDescription = "Corona",Modifier.width(40.dp).padding(start = 5.dp) )
            }
            Text(
                text = "${index}",
                modifier = Modifier.padding(start = 5.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.displaySmall
            )

        }
        Text(
            text = rankingItem.user.userName,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "${rankingItem.score}",
            modifier = Modifier.weight(0.8f).padding(end = 10.dp),
            textAlign = TextAlign.End,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge
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
