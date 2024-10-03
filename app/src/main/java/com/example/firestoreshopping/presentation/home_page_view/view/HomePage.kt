package com.example.firestoreshopping.presentation.home_page_view.view


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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
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
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.example.firestoreshopping.R
import com.example.firestoreshopping.Screan
import com.example.firestoreshopping.presentation.home_page_view.HomePageViewModel
import com.google.gson.Gson
import java.net.URLEncoder


@Composable
fun HomePage(
    viewModel: HomePageViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    val stateHome = viewModel.state.collectAsState()
    val stateUser = viewModel.stateUser.collectAsState()


    LaunchedEffect(true) {
        viewModel.loadCategory()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly){
                Text(
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier
                       // .align(alignment = Alignment.CenterHorizontally)
                        .padding(16.dp),
                    text = "E-COMMERCE"
                )
                LazyColumn {
                    items(stateUser.value.userList){
                        Text(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            text = "Welcome ${it.userName}")
                    }
                }
                
            }

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(200.dp),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.indirim2),
                contentDescription = ""
            )
        }

        if (stateHome.value.isLoading) {
            CircularProgressIndicator(
                color = Color.Red,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )
        }
        else{
            LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(10.dp)) {
                items(stateHome.value.categoryList) { category ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(230.dp)
                            .clickable {
                                val movieObject = Gson().toJson(category)
                                val encodedMovieObject = URLEncoder.encode(movieObject, "UTF-8")
                                navController.navigate(Screan.ProductPage.route + "/$encodedMovieObject")
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
                                    model = category.categoryImage, imageLoader = ImageLoader(context)
                                ),
                                contentDescription = ""
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                modifier = Modifier.padding(8.dp),
                                text = category.categoryName
                            )
                        }
                    }
                }
            }
        }

    }
}
