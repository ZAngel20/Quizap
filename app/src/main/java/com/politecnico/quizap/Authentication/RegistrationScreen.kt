package com.politecnico.quizap.Authentication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.politecnico.quizap.Components.LoginTextField
import com.politecnico.quizap.Components.RoundedButton
import com.politecnico.quizap.R
import com.politecnico.quizap.data.Model.API.MiAPI
import com.politecnico.quizap.data.Model.MyMessage
import com.politecnico.quizap.data.Model.UserDto
import com.politecnico.quizap.data.Model.UserRepository
import com.politecnico.quizap.navigation.AppScreens
import com.politecnico.quizap.ui.theme.QuizapTheme
import kotlinx.coroutines.launch

class RegistrationScreen : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
@Composable
fun RegistrationScreen(navController: NavController) {
    val colors = listOf(Color(0xFF0CADC1), Color(0xFFB85DE3))
    val brush = Brush.verticalGradient(colors)
    val nameValue = remember { mutableStateOf("") }
    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val confirmPasswordValue = remember { mutableStateOf("") }
    var somethingWrong = remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var confirmPasswordVisibility by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    Box(
      modifier = Modifier
          .fillMaxSize()
          .background(brush),
    ){

        Column(modifier = Modifier
            .verticalScroll(rememberScrollState())
        ) {

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(13.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "Login Image",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 60.dp, vertical = 10.dp)
                )

                LoginTextField(
                    textFieldValue = nameValue,
                    textLabel = stringResource(id = R.string.name),
                    keyboardType = KeyboardType.Text,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )

                LoginTextField(
                    textFieldValue = emailValue,
                    textLabel = stringResource(id = R.string.email),
                    keyboardType = KeyboardType.Email,
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    imeAction = ImeAction.Next
                )

                LoginTextField(
                    textFieldValue = passwordValue,
                    textLabel = stringResource(id = R.string.password),
                    keyboardType = KeyboardType.Password,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                passwordVisibility = !passwordVisibility
                            }
                        ) {
                            Icon(
                                imageVector = if (passwordVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Toggle Password Icon"
                            )
                        }
                    },
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
                )

                LoginTextField(
                    textFieldValue = confirmPasswordValue,
                    textLabel = stringResource(id = R.string.rpassword ),
                    keyboardType = KeyboardType.Password,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            // TODO("REGISTRATION")
                        }
                    ),
                    imeAction = ImeAction.Done,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                confirmPasswordVisibility = !confirmPasswordVisibility
                            }
                        ) {
                            Icon(
                                imageVector = if (confirmPasswordVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Toggle Password Icon"
                            )
                        }
                    },
                    visualTransformation = if (confirmPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation()
                )
                RoundedButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.registernow),
                    displayProgressBar = false,
                    onClick = {
                        if (
                            emailValue.value.isNotEmpty() &&
                            passwordValue.value.isNotEmpty() &&
                            confirmPasswordValue.value.isNotEmpty() &&
                            nameValue.value.isNotEmpty()) {
                                if (passwordValue.value.equals(confirmPasswordValue.value, ignoreCase = false)) {
                                    if (isEmail(emailValue.value)) {
                                        if (isValidPassword(passwordValue.value) == 0) {
                                            somethingWrong.value = ""
                                            val userRepository: UserRepository by lazy {
                                                UserRepository(
                                                    MiAPI.instance
                                                )
                                            }
                                            val user = UserDto(
                                                nameValue.value,
                                                emailValue.value,
                                                passwordValue.value
                                            )
                                            scope.launch {

                                                val result: MyMessage = userRepository.signUp(user)
                                                val myMessage: MyMessage = result
                                                try {
                                                    if (myMessage.statusCode == 0) {
                                                        Log.d("Resultado Mensaje:",result.toString())
                                                        Log.d("Resultado Mensaje:",myMessage.toString())
                                                            navController.navigate("verifyToken/${emailValue.value}")
                                                    } else if (myMessage.statusCode == 400) {
                                                        Log.d("Resultado 409:", result.toString())
                                                        Log.d("Resultado 409:",myMessage.toString())
                                                        somethingWrong.value = context.getString(R.string.nameissue)
                                                    }else if (myMessage.statusCode == 409) {
                                                        Log.d("Resultado 409:", result.toString())
                                                        Log.d("Resultado 409:",myMessage.toString())
                                                        somethingWrong.value = context.getString(R.string.emailUsed)
                                                    } else {
                                                        Log.d("Resultado Error:", result.toString())
                                                        Log.d("Resultado Error:",myMessage.toString())
                                                        somethingWrong.value = context.getString(R.string.noWifi)

                                                    }
                                                } catch (e: Exception) {
                                                    Log.d("Resultado Error:", result.toString())
                                                    Log.d("Resultado Error:", myMessage.toString())
                                                    somethingWrong.value = context.getString(R.string.noWifi)
                                                }
                                            }
                                        } else if (isValidPassword(passwordValue.value) == 1){

                                            somethingWrong.value = context.getString(R.string.passissue1)
                                        } else {
                                            somethingWrong.value = context.getString(R.string.passissue2)
                                        }
                                    } else {
                                        somethingWrong.value = context.getString(R.string.emailWrong)
                                    }
                                } else {
                                    somethingWrong.value = context.getString(R.string.passissue3)
                                }
                            } else {
                            somethingWrong.value = context.getString(R.string.MissingFields)
                        }
                    }
                )
                if (somethingWrong.value.isNotEmpty() && !somethingWrong.value.equals(""))
                {
                    Text(
                        text = somethingWrong.value,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                }
                FloatingActionButton(
                    containerColor = Color(0xFF4472c0),
                    modifier = Modifier
                        .padding(horizontal = 5.dp, vertical = 10.dp),
                    onClick = {
                        navController.navigate(route = AppScreens.LoginScreen.route)
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Forward Icon",
                        tint = Color.White
                    )
            }
            }
            }
        }
    }
fun isEmail(text: String): Boolean {
    val emailRegex = """^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$""".toRegex()
    return emailRegex.matches(text)
}
fun isValidPassword(text: String): Int {
    val passwordRegex = """((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$""".toRegex()
    return when {
        text.length < 8 -> 1
        !passwordRegex.matches(text) -> 2
        else -> 0
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    val navController = rememberNavController()
    QuizapTheme {
        RegistrationScreen(navController = navController)
    }
}