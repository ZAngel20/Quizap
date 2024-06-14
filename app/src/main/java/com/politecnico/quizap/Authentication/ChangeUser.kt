package com.codelab.basiclayouts.Authentication

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.politecnico.quizap.Components.LoginTextField
import com.politecnico.quizap.Components.RoundedButton
import com.politecnico.quizap.R
import com.politecnico.quizap.ViewModel.UserViewModel
import com.politecnico.quizap.data.Model.API.MiAPI
import com.politecnico.quizap.data.Model.MyMessage
import com.politecnico.quizap.data.Model.UserName
import com.politecnico.quizap.data.Model.UserRepository
import com.politecnico.quizap.navigation.AppScreens
import com.politecnico.quizap.ui.theme.QuizapTheme
import kotlinx.coroutines.launch

@Composable
fun ChangeUser(navController: NavController) {
    val colors = listOf(Color(0xFF0CADC1), Color(0xFFB85DE3))
    val brush = Brush.verticalGradient(colors)
    val nameValue = rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush),
    ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                /*verticalArrangement = Arrangement.SpaceEvenly*/
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "Login Image",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 60.dp, vertical = 10.dp)
                )

                Spacer(modifier = Modifier.height(25.dp))

                    Box(modifier = Modifier.padding(horizontal = 10.dp)) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {

                            Text(
                                color = Color.White,
                                text = stringResource(id = R.string.writeNewUser),
                                style = MaterialTheme.typography.titleLarge,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(25.dp))
                            LoginTextField(
                                textFieldValue = nameValue,
                                textLabel = stringResource(id = R.string.name),
                                keyboardType = KeyboardType.Email,
                                keyboardActions = KeyboardActions(
                                    onNext = {
                                        focusManager.moveFocus(FocusDirection.Down)
                                    }
                                ),
                                imeAction = ImeAction.Next
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(25.dp))

                    RoundedButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 15.dp),
                        text = stringResource(id = R.string.send),
                        displayProgressBar = false,
                        onClick = {
                            val userRepository: UserRepository by lazy {
                                UserRepository(
                                    MiAPI.instance
                                )
                            }
                            val user = UserName(nameValue.value)
                            scope.launch {
                                Log.d("NewUsername", nameValue.value)
                                val result: MyMessage = userRepository.changeUser(context,user)
                                val myMessage: MyMessage = result
                                try {
                                    if (myMessage.statusCode == 0) {
                                        Log.d("Resultado Mensaje:", result.toString())
                                        Log.d("Resultado Mensaje:", myMessage.toString())
                                        val userViewModel = UserViewModel.getInstance()
                                        userViewModel.changeUserName(user)
                                        navController.navigate(AppScreens.ProfileScreen.route) {
                                            popUpTo(AppScreens.ProfileScreen.route) {
                                                inclusive = true
                                            }
                                        }
                                    } else {
                                        Log.d("Resultado Error:", result.toString())
                                        Log.d("Resultado Error:", myMessage.toString())
                                    }
                                } catch (e: Exception) {
                                    Log.d("Resultado Error:", result.toString())
                                    Log.d("Resultado Error:", myMessage.toString())
                                }
                            }
                        }
                    )


            }
    }
}

@Preview(showBackground = true)
@Composable
fun ChangeUserScreen() {
    val navController = rememberNavController()
    QuizapTheme {
        ChangeUser(navController = navController)
    }
}