package com.example.firestoreshopping.presentation.saved_adress

import com.example.firestoreshopping.domain.model.LocationUser


data class SavedAdressState(
    val adresList:List<LocationUser> = emptyList(),
    val isError:String="",
    val isLoading:Boolean=false,
)
