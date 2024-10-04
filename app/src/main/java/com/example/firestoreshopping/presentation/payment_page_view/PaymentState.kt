package com.example.firestoreshopping.presentation.payment_page_view

import com.example.firestoreshopping.domain.model.Card

data class PaymentState(
    val locatinList:List<Card> = emptyList(),
    val isError: String="",
    val isLoading:Boolean=false
)
