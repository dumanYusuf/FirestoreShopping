package com.example.firestoreshopping.presentation.favori_page_view

import com.example.firestoreshopping.domain.model.Favori

data class FavoriState(
    val favoriList:List<Favori> = emptyList(),
    val isErorr:String="",
    val isLoading:Boolean=false
)