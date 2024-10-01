package com.example.firestoreshopping

sealed class Screan (val route:String){


    object LoginPage:Screan("login")
    object RegisterPage:Screan("register")
    object HomePage:Screan("home")
    object FavoriPage:Screan("favori")
    object BasketPage:Screan("basket")
    object PersonPage:Screan("person")
    object ProductPage:Screan("product")
    object DetailPage:Screan("detail")




}