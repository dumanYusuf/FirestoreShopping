package com.example.firestoreshopping.presentation.product_page_view

import com.example.firestoreshopping.domain.model.Products

data class ProductState(
    val productList:List<Products> = emptyList(),
    val isError:String="",
    val isLoading:Boolean=false
)
