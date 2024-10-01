package com.example.firestoreshopping.presentation.home_page_view

import com.example.firestoreshopping.domain.model.Category

data class HomePageState(
    val categoryList:List<Category> = emptyList(),
    val isErorr:String="",
    val isLoading:Boolean=false
)
