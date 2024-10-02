package com.example.firestoreshopping.presentation.basket_page_view

import com.example.firestoreshopping.domain.model.Basket

data class BasketState(
    val basketList:List<Basket> = emptyList(),
    val isErorr:String="",
    val isLoading:Boolean=false,
    val totalPrice: Double = 0.0 // Toplam fiyat için bir alan ekliyoruz

)
