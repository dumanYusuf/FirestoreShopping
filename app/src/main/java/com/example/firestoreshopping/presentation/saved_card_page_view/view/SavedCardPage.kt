package com.example.firestoreshopping.presentation.saved_card_page_view.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.firestoreshopping.R
import com.example.firestoreshopping.presentation.saved_card_page_view.SavedViewModel
import kotlinx.coroutines.launch

@Composable
fun SavedCardPage(
    viewModel: SavedViewModel= hiltViewModel(),
    onBackPressed:()->Unit
) {

    LaunchedEffect(key1 = true) {
        viewModel.loadSavedCard()
    }

    val state=viewModel.state.collectAsState()

    Column (modifier = Modifier.fillMaxSize()){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
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
                    text = "Kayıtlı Kartlarım"
                )
            }
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
            if (state.value.savedCardList.isEmpty()){
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        text = "Kayıtlı Kartınız Bulunmuyor")
                }
            }
            LazyColumn (modifier = Modifier.fillMaxSize()){
                items(state.value.savedCardList){
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .size(80.dp)) {
                        Row (modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween){
                            Icon(
                                modifier = Modifier.size(35.dp),
                                painter = painterResource(id = R.drawable.card), contentDescription ="" )
                            Column {
                                Text(
                                    fontSize = 20.sp,
                                    text = it.cardHolder)
                                Text(
                                    fontSize = 20.sp,
                                    text ="**** **** ****"+it.cardNumber.takeLast(4))
                            }
                            Icon(
                                modifier = Modifier
                                    .size(35.dp)
                                    .clickable {

                                    },
                                painter = painterResource(id = R.drawable.delete), contentDescription ="" )
                        }
                    }
                }
            }

        }

    }

}