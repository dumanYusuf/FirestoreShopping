package com.example.firestoreshopping.presentation.person_page_view.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.firestoreshopping.Screan
import com.example.firestoreshopping.presentation.person_page_view.PersonViewModel

@Composable
fun PersonPage(
    viewModel: PersonViewModel= hiltViewModel(),

) {

    Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Button(onClick = {
            viewModel.logOut()
        }) {
            Text(text = "LogOut")
        }
    }
}