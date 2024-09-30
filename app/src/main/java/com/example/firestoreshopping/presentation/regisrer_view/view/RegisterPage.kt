package com.example.firestoreshopping.presentation.login_view.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.firestoreshopping.R
import com.example.firestoreshopping.Screan
import com.example.firestoreshopping.domain.model.Users
import com.example.firestoreshopping.presentation.component.MyTextField
import com.example.firestoreshopping.presentation.regisrer_view.RegisterViewModel

@Composable
fun RegisterPage(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val userName = remember { mutableStateOf(TextFieldValue("")) }
    val userLastName = remember { mutableStateOf(TextFieldValue("")) }
    val userMail = remember { mutableStateOf(TextFieldValue("")) }
    val userPassword = remember { mutableStateOf(TextFieldValue("")) }
    val userConfirmPassword = remember { mutableStateOf(TextFieldValue("")) }

    val state = viewModel.state.collectAsState().value

    var emailError by remember { mutableStateOf("") }
    var userNameError by remember { mutableStateOf("") }
    var userLastNameError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var confirmPasswordError by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(125.dp),
            contentScale = ContentScale.FillHeight,
            painter = painterResource(id = R.drawable.register),
            contentDescription = ""
        )
        Text(
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            text = "Register"
        )
        Spacer(modifier = Modifier.height(5.dp))

        MyTextField(
            modifier = Modifier.padding(10.dp),
            value = userMail.value,
            hint = "Enter Your Mail",
            onValueChange = {
                userMail.value = it
                emailError = if (!it.text.contains("@")) "Please enter a valid email with @" else ""
            },
            label = "Mail"
        )
        if (emailError.isNotEmpty()) {
            Text(
                text = emailError,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 10.dp)
            )
        }

        MyTextField(
            modifier = Modifier.padding(10.dp),
            value = userName.value,
            hint = "Enter Your First Name",
            onValueChange = {
                userName.value = it
                userNameError = if (it.text.isEmpty()) "First name cannot be empty" else ""
            },
            label = "First Name"
        )
        if (userNameError.isNotEmpty()) {
            Text(
                text = userNameError,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 10.dp)
            )
        }

        MyTextField(
            modifier = Modifier.padding(10.dp),
            value = userLastName.value,
            hint = "Enter Your Last Name",
            onValueChange = {
                userLastName.value = it
                userLastNameError = if (it.text.isEmpty()) "Last name cannot be empty" else ""
            },
            label = "Last Name"
        )
        if (userLastNameError.isNotEmpty()) {
            Text(
                text = userLastNameError,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 10.dp)
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        MyTextField(
            modifier = Modifier.padding(10.dp),
            value = userPassword.value,
            hint = "Enter Your Password",
            isPassword = true,
            onValueChange = {
                userPassword.value = it
                passwordError =
                    if (it.text.length < 6) "Password must be at least 6 characters" else ""
            },
            label = "Password"
        )
        if (passwordError.isNotEmpty()) {
            Text(
                text = passwordError,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 10.dp)
            )
        }

        MyTextField(
            modifier = Modifier.padding(10.dp),
            value = userConfirmPassword.value,
            hint = "Enter Your Confirm Password",
            isPassword = true,
            onValueChange = {
                userConfirmPassword.value = it
                confirmPasswordError =
                    if (it.text != userPassword.value.text) "Passwords do not match" else ""
            },
            label = "Confirm Password"
        )
        if (confirmPasswordError.isNotEmpty()) {
            Text(
                text = confirmPasswordError,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 10.dp)
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            onClick = {
                if (emailError.isEmpty() && userNameError.isEmpty() && userLastNameError.isEmpty() &&
                    passwordError.isEmpty() && confirmPasswordError.isEmpty()
                ) {
                    val newUser = Users(
                        userId = "",
                        userName = userName.value.text,
                        userLastName = userLastName.value.text,
                        userMail = userMail.value.text,
                        userProfilUrl = ""
                    )
                    viewModel.register(newUser, password = userPassword.value.text)
                }
            }
        ) {
            Text(fontSize = 24.sp, text = "Register")
            if (state.isLoading) {
                CircularProgressIndicator(
                    color = Color.Red,
                    modifier = Modifier
                        .size(30.dp)

                )
            }
        }


        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Or login with..."
        )
        Spacer(modifier = Modifier.padding(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                modifier = Modifier.height(30.dp),
                painter = painterResource(id = R.drawable.google),
                contentDescription = ""
            )
            Image(
                modifier = Modifier.height(30.dp),
                painter = painterResource(id = R.drawable.facebook),
                contentDescription = ""
            )
        }

        Spacer(modifier = Modifier.padding(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Already have an account?")
            Text(
                modifier = Modifier.clickable {
                    navController.navigate(Screan.LoginPage.route)
                },
                fontWeight = FontWeight.Bold,
                text = "Login"
            )

        }
    }

        if (state.isError.isNotBlank()) {
            Text(
                text = state.isError,
                color = Color.Red,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
            )
        }

        if (state.isSuccess) {
            Text(
                text = "Registration successful!",
                color = Color.Green,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }

