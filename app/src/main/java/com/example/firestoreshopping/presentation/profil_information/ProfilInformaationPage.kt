package com.example.firestoreshopping.presentation.profil_information

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firestoreshopping.R
import com.example.firestoreshopping.domain.model.Users

@Composable
fun ProfilInformationPage(
    users: Users,
    onBackPressed: () -> Unit,
    ) {
    Column(modifier = Modifier.fillMaxSize()) {
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
                text = "Profil Bilgileri"
            )
        }

        Column (modifier = Modifier.padding(10.dp)){
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                text = "E-Posta")
            Text(
                fontSize = 20.sp,
                text = users.userMail)
            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                text = "Ad")
            Text(
                fontSize = 20.sp,
                text = users.userName)
            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                text = "Soyad")
            Text(
                fontSize = 20.sp,
                text = users.userLastName)
        }
    }
}