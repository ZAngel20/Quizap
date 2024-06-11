package com.codelab.basiclayouts.Authentication

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
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.politecnico.quizap.Components.LoginTextField
import com.politecnico.quizap.Components.RoundedButton
import com.politecnico.quizap.R
import com.politecnico.quizap.ui.theme.QuizapTheme

@Composable
fun ForgotPassword(navController: NavController) {
    val colors = listOf(Color(0xFF0CADC1), Color(0xFFB85DE3))
    val brush = Brush.verticalGradient(colors)
    val emailValue = rememberSaveable { mutableStateOf("") }
    val codeValue = rememberSaveable { mutableStateOf("") }
    val passwordValue = rememberSaveable { mutableStateOf("") }
    var codigoRecibido by remember { mutableStateOf(false) }
    var codigoCorrecto by remember { mutableStateOf(false) }
    var passwordVisibility by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
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
                                style = MaterialTheme.typography.titleMedium,
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
                            codigoRecibido = true
                            /*if (emailValue.value.isNotEmpty() && passwordValue.value.isNotEmpty()) {
                            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                                emailValue.value,
                                passwordValue.value
                            ).addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success")
                                    val user = FirebaseAuth.getInstance().currentUser
                                    navController.navigate(route = AppScreens.FirstScreen.route)
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                                    Toast.makeText(
                                        navController.context,
                                        "Authentication failed.",
                                        Toast.LENGTH_SHORT
                                    ).show(
                                })
                            }
                        }*/
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
                                text = stringResource(id = R.string.youcode),
                                style = MaterialTheme.typography.titleMedium,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(25.dp))
                            LoginTextField(
                                textFieldValue = codeValue,
                                textLabel = stringResource(id = R.string.codigo),
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



                    RoundedButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 15.dp),
                        text = stringResource(id = R.string.confirmarCodigo),
                        displayProgressBar = false,
                        onClick = {
                            codigoCorrecto = !codigoCorrecto
                            /*if (emailValue.value.isNotEmpty() && passwordValue.value.isNotEmpty()) {
                                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                                    emailValue.value,
                                    passwordValue.value
                                ).addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:success")
                                        val user = FirebaseAuth.getInstance().currentUser
                                        navController.navigate(route = AppScreens.FirstScreen.route)
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                                        Toast.makeText(
                                            navController.context,
                                            "Authentication failed.",
                                            Toast.LENGTH_SHORT
                                        ).show(
                                    })
                                }
                            }*/
                        }
                    )

                } else {
                    if (codigoCorrecto) {
                        Box(modifier = Modifier.padding(horizontal = 10.dp)) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(15.dp)
                            ) {
                                Text(
                                    color = Color.White,
                                    text = stringResource(id = R.string.youpass),
                                    style = MaterialTheme.typography.titleMedium,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(25.dp))
                                LoginTextField(
                                    textFieldValue = passwordValue,
                                    textLabel = stringResource(id = R.string.NewPassword),
                                    keyboardType = KeyboardType.Password,
                                    keyboardActions = KeyboardActions(
                                        onDone = {
                                            focusManager.clearFocus()

                                            // TODO("LOGIN")
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
                                Spacer(modifier = Modifier.height(25.dp))

                            RoundedButton(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp, vertical = 15.dp),
                                text = stringResource(id = R.string.confirmarContraseña),
                                displayProgressBar = false,
                                onClick = {
                                    /*if (emailValue.value.isNotEmpty() && passwordValue.value.isNotEmpty()) {
                                        FirebaseAuth.getInstance().signInWithEmailAndPassword(
                                            emailValue.value,
                                            passwordValue.value
                                        ).addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                // Sign in success, update UI with the signed-in user's information
                                                Log.d(TAG, "signInWithEmail:success")
                                                val user = FirebaseAuth.getInstance().currentUser
                                                navController.navigate(route = AppScreens.FirstScreen.route)
                                            } else {
                                                // If sign in fails, display a message to the user.
                                                Log.w(TAG, "signInWithEmail:failure", task.exception)
                                                Toast.makeText(
                                                    navController.context,
                                                    "Authentication failed.",
                                                    Toast.LENGTH_SHORT
                                                ).show(
                                            })
                                        }
                                    }*/
                                }
                            )
                        }
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