package com.example.firestoreshopping.presentation.login_view

data class LoginState(
    val isSuccess:Boolean=false,
    val isError:String="",
    val isLoading:Boolean=false
)
