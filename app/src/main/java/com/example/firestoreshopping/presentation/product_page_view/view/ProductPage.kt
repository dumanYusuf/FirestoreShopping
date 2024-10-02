package com.example.firestoreshopping.presentation.product_page_view.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.firestoreshopping.domain.model.Basket
import com.example.firestoreshopping.domain.model.Category
import com.example.firestoreshopping.domain.model.Favori
import com.example.firestoreshopping.domain.model.Products
import com.example.firestoreshopping.presentation.product_page_view.ProductsViewModel
import com.google.gson.Gson
import java.net.URLEncoder

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductPage(
    viewModel: ProductsViewModel = hiltViewModel(),
    categoryId: Category,
    navController: NavController,
    onBackPressed: () -> Unit
) {
    var searching = remember { mutableStateOf("") }
    var isFavorited by remember { mutableStateOf(false) }
    var isBasketed by remember { mutableStateOf(false) }




    LaunchedEffect(true) {
        viewModel.loadCategoryFilterProduct(categoryId.categoryId)
    }

    val stateProduct = viewModel.state.collectAsState()
    val stateSearch = viewModel.stateSearch.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onBackPressed() }
                ) {
                    Icon(painter = painterResource(id = R.drawable.back), contentDescription = "")
                }

                Text(
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(16.dp),
                    text = "Products"
                )

                OutlinedTextField(
                    maxLines = 1,
                    singleLine = false,
                    placeholder = { Text(text = "Searching Products...")},
                    value = searching.value,
                    onValueChange = {
                        if (!stateSearch.value.isLoading) {
                            viewModel.searchProduct(it, categoryId = categoryId.categoryId)
                            searching.value = it
                        }
                    }
                )
            }

            if (searching.value.isNotEmpty()) {
                if (stateSearch.value.isLoading) {
                    CircularProgressIndicator(
                        color = Color.Red,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(16.dp)
                    )
                } else {
                    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(10.dp)) {
                        items(stateSearch.value.productList) { products ->
                            ProductCard(products, navController,viewModel,isFavorited,isBasketed)
                        }
                    }
                }
            } else {
                if (stateProduct.value.isLoading) {
                    CircularProgressIndicator(
                        color = Color.Red,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(16.dp)
                    )
                } else {
                    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(10.dp)) {
                        items(stateProduct.value.productList) { products ->
                            ProductCard(products, navController,viewModel,true,true)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProductCard(products: Products, navController: NavController,viewModel: ProductsViewModel,isFavorited:Boolean,isBasketed:Boolean) {

    val context = LocalContext.current
    var isFavorited by remember { mutableStateOf(false) }
    var isBasketed by remember { mutableStateOf(false) }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(300.dp)
            .clickable {
                val productObject = Gson().toJson(products)
                val encodedProductObject = URLEncoder.encode(productObject, "UTF-8")
                navController.navigate(Screan.DetailPage.route + "/$encodedProductObject")
            },
        shape = MaterialTheme.shapes.medium
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop,
                painter = rememberAsyncImagePainter(
                    model = products.productImage,
                    imageLoader = ImageLoader(context)
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Icon(
                    tint = if (isFavorited) Color.Red else Color.Blue,
                    modifier = Modifier.size(35.dp).clickable {
                        val favori=Favori(favoriId = products.productId, productId =products.productId, productName = products.productName, productImage = products.productImage, productPrice = products.productPrice, isFavorited = true)
                        viewModel.addFavoriFirestore(favori)
                        isFavorited=true
                    },
                    painter = painterResource(id = R.drawable.favori),
                    contentDescription = ""
                )
                Icon(
                    tint = if (isBasketed) Color.Red else Color.Blue,
                    modifier = Modifier.size(35.dp).clickable {
                        val basket= Basket(basketId = products.productId,productId =products.productId, productName = products.productName, productImage = products.productImage, productPrice = products.productPrice, isBasket = true)
                        viewModel.addBasketFirestore(basket)
                        isBasketed=true
                    },
                    painter = painterResource(id = R.drawable.basket),
                    contentDescription = ""
                )
            }
        }
    }
}
