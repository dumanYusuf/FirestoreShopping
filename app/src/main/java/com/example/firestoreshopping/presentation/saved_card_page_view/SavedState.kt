package com.example.firestoreshopping.presentation.saved_card_page_view

import com.example.firestoreshopping.domain.model.Card
import com.example.firestoreshopping.domain.model.LocationUser

data class SavedState(
    val savedCardList:List<Card> = emptyList(),
    val isError: String="",
    val isLoading:Boolean=false
)
