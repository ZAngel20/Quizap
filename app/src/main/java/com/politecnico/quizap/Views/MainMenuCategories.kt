package com.politecnico.quizap.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.politecnico.quizap.ViewModel.CategoriesScreenViewModel
import com.politecnico.quizap.ViewModel.LevelViewModel
import com.politecnico.quizap.data.Model.Category
import com.politecnico.quizap.navigation.AppScreens
import com.politecnico.quizap.ui.theme.QuizapTheme
@Composable
fun CategoriesScreen(modifier: Modifier = Modifier, navController: NavController) {
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
            val categoriesScreenViewModel = CategoriesScreenViewModel.getInstance()
            val viewModel = remember { categoriesScreenViewModel}
            val categories by viewModel.listCategory.observeAsState(initial = emptyList())
            val state = rememberLazyListState()
            categoriesScreenViewModel.setCategories(emptyList())
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp, vertical = 30.dp),
                verticalArrangement = Arrangement.spacedBy(40.dp),
                state = state
            )   {
                    items(categories) {category ->
                        CategoryItem(navController = navController, category = category)
                    }
                }
            }

            BottomNavigation(modifier = modifier, navController = navController, selectedTab = 1)

        }
    }

    @Composable
    fun CategoryItem(navController: NavController,category: Category) {
        val levelViewModel = LevelViewModel.getInstance()
        levelViewModel.setLevels(emptyList())
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            ClickableRoundedRectangle(
                onClick = {
                    navController.navigate(AppScreens.LevelScreen.route)},
                text = category.name
            )
        }
    }

@Composable
fun ClickableRoundedRectangle(
    onClick: () -> Unit,
    text: String,
    height: Dp = 75.dp,
    cornerRadius: Dp = 20.dp
) {
    val colors = listOf(Color(0xFFB721DA), Color(0xFF82189B))
    val brush = Brush.verticalGradient(colors)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(brush, shape = RoundedCornerShape(cornerRadius))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text,
            style = MaterialTheme.typography.titleLarge,
            color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun CategoriesScreenPreview() {
    val navController = rememberNavController()
    QuizapTheme {
        CategoriesScreen(navController = navController)
    }
}
