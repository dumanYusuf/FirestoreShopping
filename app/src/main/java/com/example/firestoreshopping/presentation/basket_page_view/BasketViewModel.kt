package com.example.firestoreshopping.presentation.basket_page_view

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.example.firestoreshopping.domain.use_case.add_basket_firestore_use_case.AddBasketFirestoreUseCase
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class BasketViewModel @Inject constructor(private val addBasketFirestoreUseCase: AddBasketFirestoreUseCase):ViewModel() {


    private val _state= MutableStateFlow<BasketState>(BasketState())
    val state:StateFlow<BasketState> = _state




}