package com.example.firestoreshopping.presentation.person_page_view.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.firestoreshopping.R
import com.example.firestoreshopping.presentation.person_page_view.PersonViewModel

@Composable
fun PersonPage(
    viewModel: PersonViewModel= hiltViewModel(),
    onBackPressed: () -> Unit,

) {

    LaunchedEffect(key1 = true) {
        viewModel.getUser()
    }

    val state=viewModel.state.collectAsState()

    Column (modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = { onBackPressed() }
            ) {
                Icon(painter = painterResource(id = R.drawable.back), contentDescription = "")
            }
            Text(
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(16.dp),
                text = "E-COMMERCE"
            )
        }

        
        LazyColumn (modifier = Modifier.fillMaxWidth()){
            items(state.value.userList){
                Text(text = it.userName)
            }
        }
        
    }
    }