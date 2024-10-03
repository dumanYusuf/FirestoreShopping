package com.example.firestoreshopping.presentation.person_page_view.view


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.firestoreshopping.R
import com.example.firestoreshopping.Screan
import com.example.firestoreshopping.presentation.person_page_view.PersonViewModel
import com.google.gson.Gson
import java.net.URLEncoder

@Composable
fun PersonPage(
    viewModel: PersonViewModel= hiltViewModel(),
    onBackPressed: () -> Unit,
    navController: NavController,
    currentIndex: MutableState<Int>

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

        Spacer(modifier = Modifier.padding(10.dp))

        LazyColumn (modifier = Modifier.fillMaxWidth()){
            items(state.value.userList){
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 100.dp)) {
                    Box (
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(size = 30.dp))
                            .background(Color.Red)
                            .size(60.dp)){
                        val initials = (it.userName.firstOrNull()?.uppercase() ?: "") +
                                (it.userLastName.firstOrNull()?.uppercase() ?: "")

                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            fontSize = 24.sp,
                            color = Color.White,
                            text = initials
                        )
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    Column (modifier = Modifier.fillMaxSize()){
                        Text(
                            fontSize = 20.sp,
                            text = it.userName+it.userLastName)
                        Text(
                            fontSize = 18.sp,
                            text = it.userMail)
                    }
                }
                Spacer(modifier = Modifier.padding(15.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(8.dp)
                        .clickable {
                            val productObject = Gson().toJson(it)
                            val encodedProductObject = URLEncoder.encode(productObject, "UTF-8")
                            navController.navigate(Screan.ProfilInformationPage.route+"/$encodedProductObject")
                        }
                ) {
                    Row ( modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                        Row (verticalAlignment = Alignment.CenterVertically){
                            Icon(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = R.drawable.person),
                                contentDescription = "User Icon"
                            )
                            Text(
                                modifier = Modifier.padding(10.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                text = "Profil Bilgileri")
                        }
                        Icon(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = R.drawable.next),
                            contentDescription = "User Icon"
                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(8.dp)
                        .clickable {
                            navController.navigate(Screan.BasketPage.route)
                            currentIndex.value=2
                        }
                ) {
                    Row ( modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                        Row (verticalAlignment = Alignment.CenterVertically){
                            Icon(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = R.drawable.basket),
                                contentDescription = "User Icon"
                            )
                            Text(
                                modifier = Modifier.padding(10.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                text = "Sepet")
                        }
                        Icon(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = R.drawable.next),
                            contentDescription = "User Icon"
                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(8.dp)
                        .clickable {
                            navController.navigate(Screan.FavoriPage.route)
                            currentIndex.value=1
                        }
                ) {
                    Row ( modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                        Row (verticalAlignment = Alignment.CenterVertically){
                            Icon(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = R.drawable.favori),
                                contentDescription = "User Icon"
                            )
                            Text(
                                modifier = Modifier.padding(10.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                text = "Favori")
                        }
                        Icon(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = R.drawable.next),
                            contentDescription = "User Icon"
                        )
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(8.dp)
                        .clickable {
                            //navController.navigate(Screen.HomePage.route)
                        }
                ) {
                    Row ( modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                        Row (verticalAlignment = Alignment.CenterVertically){
                            Icon(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = R.drawable.loca),
                                contentDescription = "User Icon"
                            )
                            Text(
                                modifier = Modifier.padding(10.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                text = "Adreslerim")
                        }
                        Icon(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = R.drawable.next),
                            contentDescription = "User Icon"
                        )
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(8.dp)
                        .clickable {
                            //navController.navigate(Screen.HomePage.route)
                        }
                ) {
                    Row ( modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                        Row (verticalAlignment = Alignment.CenterVertically){
                            Icon(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = R.drawable.card),
                                contentDescription = "User Icon"
                            )
                            Text(
                                modifier = Modifier.padding(10.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                text = "Kartlarım")
                        }
                        Icon(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = R.drawable.next),
                            contentDescription = "User Icon"
                        )
                    }
                }


                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(8.dp)
                        .clickable {
                            viewModel.logOut()
                        }
                ) {
                    Row ( modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                        Row (verticalAlignment = Alignment.CenterVertically){
                            Icon(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = R.drawable.out),
                                contentDescription = "User Icon"
                            )
                            Text(
                                modifier = Modifier.padding(10.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                text = "Çıkış Yap")
                        }
                        Icon(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = R.drawable.next),
                            contentDescription = "User Icon"
                        )
                    }
                }

                    }
                }
            }
        }

