package com.politecnico.quizap.Authentication

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
import androidx.compose.runtime.remember
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
import com.politecnico.quizap.data.Model.API.MiAPI
import com.politecnico.quizap.data.Model.MyMessage
import com.politecnico.quizap.data.Model.UserRepository
import com.politecnico.quizap.data.Model.UserResend
import com.politecnico.quizap.data.Model.UserToken
import com.politecnico.quizap.navigation.AppScreens
import com.politecnico.quizap.ui.theme.QuizapTheme
import kotlinx.coroutines.launch

@Composable
fun VerificacionTokenScreen(navController: NavController, email : String) {
    val colors = listOf(Color(0xFF0CADC1), Color(0xFFB85DE3))
    val brush = Brush.verticalGradient(colors)
    val emailValue = rememberSaveable { mutableStateOf(email) }
    val tokenValue = rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    var eMessage = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
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

            /*
            Text(
                color = Color.White,
                text = "Inicia sesión",
                style = MaterialTheme.typography.bodyMedium.copy()
            )*/
            Box(modifier = Modifier.padding(horizontal = 10.dp)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Text(
                        text = "Se le ha enviado un codigo a su direccion de correo proporcionada",
                        color = Color.White,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = "Escriba el Token a continuacion",
                        color = Color.White,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                        textAlign = TextAlign.Center,
                    )
                    LoginTextField(
                        textFieldValue = tokenValue,
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
            Spacer(modifier = Modifier.height(10.dp))

            RoundedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 15.dp),
                text = stringResource(id = R.string.login),
                displayProgressBar = false,
                onClick = {
                            eMessage.value = ""
                            val userRepository: UserRepository by lazy {
                                UserRepository(
                                    MiAPI.instance
                                )
                            }
                            val user = UserToken(emailValue.value,tokenValue.value)
                            coroutineScope.launch {
                                    Log.d("email", emailValue.value)
                                    val result: MyMessage = userRepository.signUpCode(user)
                                    val myMessage: MyMessage = result
                                    try {
                                        if (myMessage.statusCode == 0) {
                                            Log.d("Resultado Mensaje:", result.toString())
                                            Log.d("Resultado Mensaje:", myMessage.toString())
                                            eMessage.value = "El Codigo es Correcto"
                                            navController.navigate(AppScreens.LoginScreen.route) {
                                                popUpTo(AppScreens.LoginScreen.route) {
                                                    inclusive = true
                                                }
                                            }
                                        } else {
                                            Log.d("Resultado Error:", result.toString())
                                            Log.d("Resultado Error:", myMessage.toString())
                                            eMessage.value =
                                                "Comprueba que tienes conexion a internet"
                                        }
                                    } catch (e: Exception) {
                                        Log.d("Resultado Error:", result.toString())
                                        Log.d("Resultado Error:", myMessage.toString())
                                        eMessage.value = "Comprueba que tienes conexion a internet"
                                    }
                            }
                }
            )
            RoundedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 15.dp),
                text = "Volver a Enviar",
                displayProgressBar = false,
                onClick = {
                    eMessage.value = ""
                    val userRepository: UserRepository by lazy {
                        UserRepository(
                            MiAPI.instance
                        )
                    }
                    val user = UserResend(emailValue.value)
                    coroutineScope.launch {
                        Log.d("email",emailValue.value)
                            val result: MyMessage = userRepository.resendCode(user)
                            val myMessage: MyMessage = result
                            try {
                                if (myMessage.statusCode == 0) {
                                    Log.d("Resultado Mensaje:", result.toString())
                                    Log.d("Resultado Mensaje:", myMessage.toString())
                                    eMessage.value = "Se ha Reenviado con Éxito"

                                } else {
                                    Log.d("Resultado Error:", result.toString())
                                    Log.d("Resultado Error:", myMessage.toString())
                                    eMessage.value = "Comprueba que tienes conexion a internet"
                                }
                            } catch (e: Exception) {
                                Log.d("Resultado Error:", result.toString())
                                Log.d("Resultado Error:", myMessage.toString())
                                eMessage.value = "Comprueba que tienes conexion a internet"
                            }
                    }
                }
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = eMessage.value,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun VerificacionTokenPreview() {
    val navController = rememberNavController()
    QuizapTheme {
        VerificacionTokenScreen(navController = navController, email = "")
    }
}