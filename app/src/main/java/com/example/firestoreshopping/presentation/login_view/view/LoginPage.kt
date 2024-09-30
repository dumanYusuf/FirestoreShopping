package com.example.firestoreshopping.presentation.login_view.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.firestoreshopping.presentation.component.MyTextField
import com.example.firestoreshopping.presentation.login_view.LoginViewModel

@Composable
fun LoginPage(
    navController: NavController,
    viewModel: LoginViewModel= hiltViewModel()
) {

    val userMail = remember { mutableStateOf(TextFieldValue("")) }
    val userPassword = remember { mutableStateOf(TextFieldValue("")) }
    var errorMessage= remember { mutableStateOf("") }

    val state =viewModel.state.collectAsState().value

    Column (modifier = Modifier.fillMaxSize()){
        Column {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.FillHeight,
                painter = painterResource(id = R.drawable.login), contentDescription = "")
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier.padding(10.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                text = "Login")
            Spacer(modifier = Modifier.height(10.dp))
            MyTextField(
                modifier = Modifier.padding(10.dp),
                isError = false,
                value =userMail.value,
                hint = "Enter Your Mail",
                onValueChange = {
                    userMail.value=it
                },
                label = "Mail")
            MyTextField(
                modifier = Modifier.padding(10.dp),
                value =userPassword.value,
                hint = "Enter Your Password",
                isPassword = true,
                isError = false,
                onValueChange = {
                    userPassword.value=it
                },
                label = "Password")
            Spacer(modifier = Modifier.height(5.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                onClick = {
                // login button go to homePage

                    viewModel.login(userMail.value.text,userPassword.value.text)
                    navController.navigate(Screan.HomePage.route)
                   /* if (userMail.value.text.isEmpty() || userMail.value.text.contains("@")){
                        errorMessage.value="Please enter a valid email with @"
                    }
                    else if (userPassword.value.text.length<6){
                        errorMessage.value="Password must be at least 6 characters. "
                    }
                    else{

                    }*/
            }) {
                Text(
                    fontSize = 24.sp,
                    text = "Login")
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
                text = "Or login with...")
            Spacer(modifier = Modifier.padding(10.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Image(
                    modifier = Modifier
                        .height(50.dp),
                    painter = painterResource(id = R.drawable.google), contentDescription = "")
                Image(
                    modifier = Modifier
                        .height(50.dp),
                    painter = painterResource(id = R.drawable.facebook), contentDescription = "")
            }

            Spacer(modifier = Modifier.padding(10.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "Don't have an account ?")
                Text(
                    modifier = Modifier.clickable {
                        navController.navigate(Screan.RegisterPage.route)
                    },
                    fontWeight = FontWeight.Bold,
                    text = "Register")
            }

        }
    }
}