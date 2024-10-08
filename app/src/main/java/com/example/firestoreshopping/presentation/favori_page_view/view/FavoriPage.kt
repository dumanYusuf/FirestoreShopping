package com.example.firestoreshopping.presentation.favori_page_view.view

import androidx.compose.foundation.Image
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
import com.example.firestoreshopping.domain.model.Category
import com.example.firestoreshopping.domain.model.Products
import com.example.firestoreshopping.presentation.favori_page_view.FavoriViewModel

@Composable
fun FavoriPage(
    viewModel: FavoriViewModel= hiltViewModel(),
    onBackPressed: () -> Unit,
    navController: NavController,
    currentIndex:MutableState<Int>
) {

    val state=viewModel.state.collectAsState()
    val context= LocalContext.current

    LaunchedEffect (true){
        viewModel.loadFavori()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = { onBackPressed() }
            ) {
                Icon(painter = painterResource(id = R.drawable.back), contentDescription ="" )
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
        if (state.value.isLoading){
            CircularProgressIndicator(
                color = Color.Red,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )
        }
        else{
            if (state.value.favoriList.isEmpty()) {
                Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .padding(top = 50.dp),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = R.drawable.emptykalp), contentDescription = "")
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(
                        fontSize = 24.sp,
                        text = "Henüz Favorilerinizde Ürün Yok")
                    TextButton(
                        onClick = {
                            navController.navigate(Screan.HomePage.route)
                            currentIndex.value=0

                        }) {
                        Text(fontSize = 20.sp,
                            text = "Alışverişe Devam Et")
                    }
                }
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.value.favoriList){favoriList->
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(10.dp)
                        .clickable {
                        }) {
                        Box(modifier = Modifier.fillMaxWidth()){
                            Image(
                                modifier = Modifier
                                    .fillMaxWidth().size(250.dp),
                                contentScale = ContentScale.Crop,
                                painter = rememberAsyncImagePainter(model =favoriList.productImage , imageLoader = ImageLoader(context) ),
                                contentDescription ="" )
                            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                                Text(
                                    modifier = Modifier.padding(10.dp),
                                    fontSize = 20.sp,
                                    text = favoriList.productName)
                                Text(
                                    modifier = Modifier.padding(10.dp),
                                    fontSize = 20.sp,
                                    text = "${favoriList.productPrice}TL")
                            }
                            Icon(
                                tint = Color.Red,
                                modifier = Modifier.clickable {
                                    viewModel.deleteProducts(favoriList)
                                    viewModel.loadFavori()
                                }.size(40.dp).align(Alignment.BottomEnd),
                                painter = painterResource(id = R.drawable.delete), contentDescription ="" )
                        }
                    }
                }
            }
        }
    }

}