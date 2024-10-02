package com.example.firestoreshopping.presentation.basket_page_view

import android.util.Log
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestoreshopping.domain.model.Basket
import com.example.firestoreshopping.domain.use_case.add_basket_firestore_use_case.AddBasketFirestoreUseCase
import com.example.firestoreshopping.domain.use_case.delete_basket_use_case.DeleteBasketUseCase
import com.example.firestoreshopping.domain.use_case.get_basktet_use_case.GetBasketUseCase
import com.example.firestoreshopping.util.Resource
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BasketViewModel @Inject constructor(
    private val getBasketUseCase: GetBasketUseCase,
    private val deleteBasketUseCase: DeleteBasketUseCase
):ViewModel() {


    private val _state= MutableStateFlow<BasketState>(BasketState())
    val state:StateFlow<BasketState> = _state


    fun loadBasket(){
        _state.value=BasketState(isLoading = true)
        viewModelScope.launch {
            getBasketUseCase.getBasket().collect{
                when(it){
                    is Resource.Success->{
                        _state.value=BasketState(basketList = it.data?: emptyList())
                        Log.e("basket success","basket success")
                    }
                    is Resource.Error->{
                        _state.value=BasketState(isErorr ="Error")
                        Log.e("basket error","basket error:${it.message}")
                    }
                    is Resource.Loading->{
                        _state.value=BasketState(isLoading = true)
                        Log.e("basket loading","basket loading")
                    }
                }
            }
        }
    }

    fun deleteBasket(id:Basket){
        viewModelScope.launch {
            deleteBasketUseCase.deleteBasket(id)
            Log.e("delete basket","success delete basket")
        }
    }

}