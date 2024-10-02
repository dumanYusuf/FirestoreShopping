package com.example.firestoreshopping.presentation.person_page_view

import com.example.firestoreshopping.domain.model.Users

data class PersonState(
    val userList:List<Users> = emptyList(),
    val isErorr:String="",
    val isLoading:Boolean=false
)
