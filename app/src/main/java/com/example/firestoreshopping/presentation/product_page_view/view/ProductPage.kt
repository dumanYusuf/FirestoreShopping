package com.example.firestoreshopping.presentation.product_page_view.view

import android.annotation.SuppressLint
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.example.firestoreshopping.R
import com.example.firestoreshopping.Screan
import com.example.firestoreshopping.domain.model.Category
import com.example.firestoreshopping.presentation.product_page_view.ProductsViewModel
import com.google.gson.Gson
import java.net.URLEncoder

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductPage(
    viewModel: ProductsViewModel= hiltViewModel(),
    categoryId:Category
) {

    LaunchedEffect (true){
        viewModel.loadCategoryFilterProduct(categoryId.categoryId)
    }

    val context= LocalContext.current
    val stateProduct=viewModel.state.collectAsState()

   Column (modifier = Modifier.fillMaxSize()){
       Column (modifier = Modifier.fillMaxWidth()){
           Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
               Text(
                   color = Color.Red,
                   fontWeight = FontWeight.Bold,
                   fontSize = 24.sp,
                   modifier = Modifier
                       .padding(16.dp),
                   text = "Products"
               )
               Icon(
                   tint = Color.Red,
                   modifier = Modifier.size(35.dp),
                   painter = painterResource(id = R.drawable.search), contentDescription = "")
           }

           if (stateProduct.value.isLoading) {
               CircularProgressIndicator(
                   color = Color.Red,
                   modifier = Modifier
                       .align(Alignment.CenterHorizontally)
                       .padding(16.dp)
               )
           }
           else{
               LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(10.dp)) {
                   items(stateProduct.value.productList) { products ->
                       Card(
                           modifier = Modifier
                               .fillMaxWidth()
                               .padding(10.dp)
                               .height(300.dp)
                               .clickable {
                                   /*val movieObject = Gson().toJson(products)
                                   val encodedMovieObject = URLEncoder.encode(movieObject, "UTF-8")
                                   navController.navigate(Screan.ProductPage.route+"/$encodedMovieObject")*/
                               },
                           shape = androidx.compose.material3.MaterialTheme.shapes.medium,

                           ) {
                           Column(horizontalAlignment = Alignment.CenterHorizontally) {
                               Image(
                                   modifier = Modifier
                                       .height(150.dp)
                                       .fillMaxWidth(),
                                   contentScale = ContentScale.Crop,
                                   painter = rememberAsyncImagePainter(
                                       model = products.productImage, imageLoader = ImageLoader(context)
                                   ),
                                   contentDescription = ""
                               )
                               Spacer(modifier = Modifier.height(8.dp))
                               Text(
                                   fontWeight = FontWeight.Bold,
                                   fontSize = 20.sp,
                                   modifier = Modifier.padding(8.dp),
                                   text = products.productName
                               )
                               Text(
                                   fontWeight = FontWeight.Bold,
                                   fontSize = 20.sp,
                                   modifier = Modifier.padding(8.dp),
                                   text = "${products.productPrice} Tl"
                               )
                               Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                                   Icon(
                                       tint = Color.Red,
                                       modifier = Modifier.size(35.dp),
                                       painter = painterResource(id = R.drawable.favori), contentDescription = "")
                                   Icon(
                                       tint = Color.Blue,
                                       modifier = Modifier.size(35.dp),
                                       painter = painterResource(id = R.drawable.basket), contentDescription = "")
                               }
                           }
                       }
                   }
               }
           }

       }
   }

}