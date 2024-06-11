package com.politecnico.quizap

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.politecnico.quizap.ViewModel.CategoriesScreenViewModel
import com.politecnico.quizap.data.Category
import com.politecnico.quizap.ui.theme.QuizapTheme
@Composable
fun RankingScreen(modifier: Modifier = Modifier, navController: NavController) {
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
            val viewModel = remember { CategoriesScreenViewModel() }
            val categories by viewModel.levels.observeAsState()
            val state = rememberLazyListState()

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
                    .fillMaxHeight().padding(horizontal = 16.dp, vertical = 30.dp),
                verticalArrangement = Arrangement.spacedBy(40.dp),
                state = state
            )   {
                    items(categories ?: emptyList()) {category ->
                        RankingItem(category = category)
                    }
                }
            }

            BottomNavigation(modifier = modifier, navController = navController, selectedTab = 1)

        }
    }

    @Composable
    fun RankingItem(category: Category) {
        var expanded by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            ClickableRoundedRectangle(
                onClick = { expanded =!expanded },
                text = category.name
            )

            if (expanded) {
                DropdownMenu(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    category.levels.forEach { level ->
                        DropdownMenuItem(
                            text = { Text(text = level.name) },
                            onClick = {
                                // Navigate to GameScreen with level data
                                //navController.navigate(route = AppScreens.GameScreen.route + "/${level.code}")
                            }
                        )
                    }
                }
            }
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
