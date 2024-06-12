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
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    Box(
      modifier = Modifier
          .fillMaxSize()
          .background(brush),
    ){
        /*FloatingActionButton(
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
        }*/
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
                /*
                Text(
                    color = Color.White,
                    text = "Registrate",
                    style = MaterialTheme.typography.bodyMedium.copy()
                )*/

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

/*
                LoginTextField(
                    textFieldValue = phoneValue,
                    textLabel = stringResource(id = R.string.phone),
                    maxChar = 10,
                    keyboardType = KeyboardType.Phone,
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    imeAction = ImeAction.Next
                )
*/
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
                                                        somethingWrong.value = "El nombre debe ser menos de 20 caracteres"
                                                    }else if (myMessage.statusCode == 409) {
                                                        Log.d("Resultado 409:", result.toString())
                                                        Log.d("Resultado 409:",myMessage.toString())
                                                        somethingWrong.value = "Ese correo ya Existe"
                                                    } else {
                                                        Log.d("Resultado Error:", result.toString())
                                                        Log.d("Resultado Error:",myMessage.toString())
                                                        somethingWrong.value = "Verifica que tienes conexión a internet"

                                                    }
                                                } catch (e: Exception) {
                                                    Log.d("Resultado Error:", result.toString())
                                                    Log.d("Resultado Error:", myMessage.toString())
                                                    somethingWrong.value =
                                                        "Comprueba que tienes conexion a internet"
                                                }
                                            }
                                        } else if (isValidPassword(passwordValue.value) == 1){

                                            somethingWrong.value = "La contraseña debe ser de al menos 8 caracteres "
                                        } else {
                                            somethingWrong.value = "La contraseña debe contener Mayusculas Minusculas y Numeros o Simbolos"
                                        }
                                    } else {
                                        somethingWrong.value = "El correo no es un correo Correcto"
                                    }
                                } else {
                                    somethingWrong.value = "Las Contraseñas no coinciden"
                                }
                            } else {
                            somethingWrong.value = "Faltan Rellenar Datos"
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

                /*
                ClickableText(
                    text = buildAnnotatedString {
                        append("¿Ya tienes una cuenta?")

                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                        ){
                            append("Inicia sesión")
                        }
                    },
                    onClick = {
                        navController.navigate(route = AppScreens.LoginScreen.route)
                    }
                )*/
                /*
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Divider(color = Color.White, modifier = Modifier.weight(1f).padding(start = 10.dp ))
                    Text(
                        text = stringResource(id = R.string.or),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Divider(color = Color.White, modifier = Modifier.weight(1f).padding(end = 10.dp ))
                }
                Spacer(modifier = Modifier.height(5.dp))
                Column(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){

                    SocialMediaButton(
                        text = stringResource(id = R.string.google),
                        onClick = { },
                        socialMediaColor = GMAILCOLOR
                    )
                }
            }
             */
            /*
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Divider(
                        modifier = Modifier.width(24.dp),
                        thickness = 1.dp,
                        color = Color.Gray
                    )

                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "O",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Black
                        )
                    )

                    Divider(
                        modifier = Modifier.width(24.dp),
                        thickness = 1.dp,
                        color = Color.Gray
                    )
                }

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Inicia sesión con",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        MaterialTheme.colorScheme.primary
                    ),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                SocialMediaButton(
                    text = "Inicia sesión con Google",
                    onClick = { },
                    socialMediaColor = GMAILCOLOR
                )

             */
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