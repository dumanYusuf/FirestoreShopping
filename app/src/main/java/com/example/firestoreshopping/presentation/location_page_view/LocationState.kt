package com.example.firestoreshopping.presentation.location_page_view

import com.example.firestoreshopping.domain.model.LocationUser

data class LocationState(

    val locatin:List<LocationUser> = emptyList(),
    val isError: String="",
    val isLoading:Boolean=false
)
