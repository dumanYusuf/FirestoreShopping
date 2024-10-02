package com.example.firestoreshopping.presentation.basket_page_view.view

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.example.firestoreshopping.R
import com.example.firestoreshopping.Screan
import com.example.firestoreshopping.presentation.basket_page_view.BasketViewModel

@Composable
fun BasketPage(
    viewModel: BasketViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    navController: NavController,
    currentIndex: MutableState<Int>

) {

    val state = viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.loadBasket()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onBackPressed() }) {
                Icon(painter = painterResource(id = R.drawable.back), contentDescription = "")
            }
            Text(
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp),
                text = "E-COMMERCE"
            )
        }
        if (state.value.isLoading) {
            CircularProgressIndicator(
                color = Color.Red,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )
        } else {
            if (state.value.basketList.isEmpty()) {
                Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .padding(top = 50.dp),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = R.drawable.emptybasket), contentDescription = "")
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(
                        fontSize = 24.sp,
                        text = "Henüz Sepetinizde  Ürün Yok")
                    TextButton(
                        onClick = {
                        navController.navigate(Screan.HomePage.route)
                            currentIndex.value=0

                    }) {
                        Text(fontSize = 20.sp,
                            text = "Alışverişe Devam Et")
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.value.basketList) { basketList ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(8.dp)
                        ) {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Image(
                                    painter = rememberAsyncImagePainter(
                                        model = basketList.productImage,
                                        imageLoader = ImageLoader(context)
                                    ),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(150.dp)
                                )
                                Column(
                                    modifier = Modifier
                                        .weight(2f)
                                        .padding(16.dp)
                                ) {
                                    Text(
                                        fontSize = 24.sp,
                                        text = basketList.productName, fontWeight = FontWeight.Bold)
                                    Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically){
                                        Text(
                                            text = "${basketList.productPrice} TL", fontSize = 20.sp)
                                        Icon(
                                            tint = Color.Red,
                                            modifier = Modifier
                                                .size(35.dp)
                                                .clickable {
                                                    viewModel.deleteBasket(basketList)
                                                    viewModel.loadBasket()
                                                },
                                            painter = painterResource(id = R.drawable.delete), contentDescription = "")
                                    }
                                   Row(modifier = Modifier
                                       .fillMaxWidth()
                                       .padding(10.dp)) {
                                       Text(
                                           fontSize = 30.sp,
                                           text = "-")
                                       Text(
                                           fontSize = 25.sp,
                                           text = "1")
                                       Text(
                                           fontSize = 30.sp,
                                           text = "+")
                                       }
                                   }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
