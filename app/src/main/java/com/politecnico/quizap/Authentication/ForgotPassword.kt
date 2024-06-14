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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.politecnico.quizap.data.Model.Services.PreferenceHelper
import com.politecnico.quizap.data.Model.UserPass
import com.politecnico.quizap.data.Model.UserRepository
import com.politecnico.quizap.data.Model.UserResend
import com.politecnico.quizap.navigation.AppScreens
import com.politecnico.quizap.ui.theme.QuizapTheme
import kotlinx.coroutines.launch

@Composable
fun ForgotPassword(navController: NavController) {
    val colors = listOf(Color(0xFF0CADC1), Color(0xFFB85DE3))
    val brush = Brush.verticalGradient(colors)
    val userViewModel = UserViewModel.getInstance()
    val emailValue = rememberSaveable { mutableStateOf("") }
    val codeValue = rememberSaveable { mutableStateOf("") }
    val passwordValue = rememberSaveable { mutableStateOf("") }
    var codigoRecibido by remember { mutableStateOf(false) }
    var codigoCorrecto by remember { mutableStateOf(false) }
    var passwordVisibility by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    if (userViewModel.email.value.isNullOrEmpty())
    {} else {
        emailValue.value = userViewModel.email.value!!
    }

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
                if (!codigoRecibido) {
                    Box(modifier = Modifier.padding(horizontal = 10.dp)) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {

                            Text(
                                color = Color.White,
                                text = stringResource(id = R.string.youforgot),
                                style = MaterialTheme.typography.titleLarge,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(25.dp))
                            LoginTextField(
                                textFieldValue = emailValue,
                                textLabel = stringResource(id = R.string.email),
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
                        text = stringResource(id = R.string.recover),
                        displayProgressBar = false,
                        onClick = {

                            val userRepository: UserRepository by lazy {
                                UserRepository(
                                    MiAPI.instance
                                )
                            }
                            val user = UserResend(emailValue.value)
                            scope.launch {
                                Log.d("email",emailValue.value)
                                val result: MyMessage = userRepository.requestChangePass(context,user)
                                val myMessage: MyMessage = result
                                try {
                                    if (myMessage.statusCode == 0) {
                                        Log.d("Resultado Mensaje:", result.toString())
                                        Log.d("Resultado Mensaje:", myMessage.toString())
                                        codigoRecibido = true
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

                } else if (codigoRecibido && !codigoCorrecto){
                    Box(modifier = Modifier.padding(horizontal = 10.dp)) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {

                            Text(
                                color = Color.White,
                                text = stringResource(id = R.string.TCodeSent),
                                style = MaterialTheme.typography.titleLarge,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(25.dp))
                            LoginTextField(
                                textFieldValue = codeValue,
                                textLabel = stringResource(id = R.string.token),
                                keyboardType = KeyboardType.Text,
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
                        Box(modifier = Modifier.padding(horizontal = 10.dp)) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(15.dp)
                            ) {
                                Text(
                                    color = Color.White,
                                    text = stringResource(id = R.string.writeNewPass),
                                    style = MaterialTheme.typography.titleLarge,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                LoginTextField(
                                    textFieldValue = passwordValue,
                                    textLabel = stringResource(id = R.string.password),
                                    keyboardType = KeyboardType.Password,
                                    keyboardActions = KeyboardActions(
                                        onDone = {
                                            focusManager.clearFocus()

                                            /*TODO LLAMADA*/
                                        }
                                    ),
                                    imeAction = ImeAction.Done,
                                    trailingIcon = {
                                        IconButton(
                                            onClick = {
                                                passwordVisibility = !passwordVisibility
                                            }
                                        ) {
                                            Icon(
                                                imageVector = if (passwordVisibility) {
                                                    Icons.Default.Visibility
                                                } else {
                                                    Icons.Default.VisibilityOff
                                                },
                                                contentDescription = "Toggle Password Icon"
                                            )
                                        }
                                    },
                                    visualTransformation = if (passwordVisibility) {
                                        VisualTransformation.None
                                    } else {
                                        PasswordVisualTransformation()
                                    }
                                )
                                Spacer(modifier = Modifier.height(10.dp))

                            RoundedButton(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp, vertical = 5.dp),
                                text = stringResource(id = R.string.send),
                                displayProgressBar = false,
                                onClick = {
                                    val userRepository: UserRepository by lazy {
                                        UserRepository(
                                            MiAPI.instance
                                        )
                                    }
                                    val user = UserPass(emailValue.value,codeValue.value,passwordValue.value)
                                    scope.launch {
                                        val result: MyMessage = userRepository.changePass(context,user)
                                        val myMessage: MyMessage = result
                                        try {
                                            if (myMessage.statusCode == 0) {
                                                Log.d("Resultado Mensaje:", result.toString())
                                                Log.d("Resultado Mensaje:", myMessage.toString())
                                                PreferenceHelper.setToken(context,"")
                                                navController.navigate(AppScreens.LoginScreen.route) {
                                                    popUpTo(AppScreens.LoginScreen.route) {
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
            }
        }
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordScreen() {
    val navController = rememberNavController()
    QuizapTheme {
        ForgotPassword(navController = navController)
    }
}