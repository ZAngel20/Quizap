package com.codelab.basiclayouts.Authentication

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.politecnico.quizap.Components.LoginTextField
import com.politecnico.quizap.Components.RoundedButton
import com.politecnico.quizap.Components.SocialMediaButton
import com.politecnico.quizap.Components.TransparentTextField
import com.politecnico.quizap.R
import com.politecnico.quizap.navigation.AppScreens
import com.politecnico.quizap.ui.theme.GMAILCOLOR
import com.politecnico.quizap.ui.theme.QuizapTheme

@Composable
fun LoginScreen(navController: NavController) {
    val colors = listOf(Color(0xFF0CADC1), Color(0xFFB85DE3))
    val brush = Brush.verticalGradient(colors)
    val emailValue = rememberSaveable { mutableStateOf("") }
    val passwordValue = rememberSaveable { mutableStateOf("") }
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
                    painter = painterResource(id = R.drawable.ic_logogris),
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
                    LoginTextField(
                        textFieldValue = emailValue,
                        textLabel = "Correo",
                        keyboardType = KeyboardType.Email,
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        ),
                        imeAction = ImeAction.Next
                    )

                    LoginTextField(
                        textFieldValue = passwordValue,
                        textLabel = "Contraseña",
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
                }
            }
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    color = Color.White,
                    text = "¿Has olvidado la contraseña?",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.End
                )

                RoundedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 15.dp),
                    text = "Entrar",
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Divider(color = Color.White, modifier = Modifier.weight(1f).padding(start = 10.dp ))
                    Text(
                        text = "OR",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Divider(color = Color.White, modifier = Modifier.weight(1f).padding(end = 10.dp ))
                }
                Spacer(modifier = Modifier.height(25.dp))
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
                }


                Box(modifier = Modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter) {
                    ClickableText(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp),
                        text = buildAnnotatedString {
                            append("¿No tienes cuenta aún? ")

                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("Regístrate")
                            }
                        }
                    ) {
                        navController.navigate(route = AppScreens.RegistrationScreen.route)
                    }
                }

            }
        }
    }

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    QuizapTheme {
        LoginScreen(navController = navController)
    }
}