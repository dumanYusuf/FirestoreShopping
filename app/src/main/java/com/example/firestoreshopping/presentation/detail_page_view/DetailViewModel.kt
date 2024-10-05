package com.example.firestoreshopping.presentation.detail_page_view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestoreshopping.domain.model.Basket
import com.example.firestoreshopping.domain.use_case.add_basket_firestore_use_case.AddBasketFirestoreUseCase
import com.example.firestoreshopping.domain.use_case.get_basktet_use_case.GetBasketUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val addBasketUseCase: AddBasketFirestoreUseCase):ViewModel(){


    fun addBasketFirestore(products: Basket){
        viewModelScope.launch {
            addBasketUseCase.addBasket(products)
            Log.e("adedded basket,","success add basket ")
        }
    }


}