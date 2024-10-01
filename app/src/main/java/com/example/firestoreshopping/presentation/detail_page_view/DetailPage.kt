package com.example.firestoreshopping.presentation.detail_page_view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.example.firestoreshopping.R
import com.example.firestoreshopping.domain.model.Products


@Composable
fun DetailPage(
    product: Products,
    onBackPressed: () -> Unit
) {
    val context = LocalContext.current
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = { onBackPressed() }
            ) {
                Icon(painter = painterResource(id = R.drawable.back), contentDescription ="" )
            }
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                text = product.productName
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp),
                contentScale = ContentScale.Crop,
                painter = rememberAsyncImagePainter(
                    model = product.productImage,
                    imageLoader = ImageLoader(context)
                ), contentDescription = ""
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Text(text = "${product.productPrice} TL")
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Blue,
                    ),
                    onClick = { /*TODO*/ }) {
                    Text(
                        color = Color.White,
                        fontSize = 20.sp,
                        text = "Sepete Ekle"
                    )
                }
            }
        }
    }
}
