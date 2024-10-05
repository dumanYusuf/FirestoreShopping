package com.example.firestoreshopping.presentation.payment_page_view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestoreshopping.domain.model.Card
import com.example.firestoreshopping.domain.use_case.add_to_card_firestore_use_case.AddCardUseCase
import com.example.firestoreshopping.presentation.location_page_view.LocationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(private val addCardUseCase: AddCardUseCase):ViewModel(){


    private val _state= MutableStateFlow<PaymentState>(PaymentState())
    val state: StateFlow<PaymentState> = _state

    fun addCard(card: Card){
        viewModelScope.launch {
            addCardUseCase.addCard(card)
            Log.e("card success","card sucess firestore")
        }
    }



}