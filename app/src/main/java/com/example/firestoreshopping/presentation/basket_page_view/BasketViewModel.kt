package com.example.firestoreshopping.presentation.basket_page_view

import android.util.Log
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestoreshopping.domain.model.Basket
import com.example.firestoreshopping.domain.use_case.delete_basket_use_case.DeleteBasketUseCase
import com.example.firestoreshopping.domain.use_case.get_basktet_use_case.GetBasketUseCase
import com.example.firestoreshopping.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BasketViewModel @Inject constructor(
    private val getBasketUseCase: GetBasketUseCase,
    private val deleteBasketUseCase: DeleteBasketUseCase,

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


    fun increaseQuantity(basketItem: Basket) {
        viewModelScope.launch {
            val updatedList = _state.value.basketList.map { item ->
                if (item == basketItem) item.copy(quantity = item.quantity + 1)
                else item
            }
            _state.value = _state.value.copy(basketList = updatedList)
            updateTotalPrice()
        }
    }

    fun decreaseQuantity(basketItem: Basket) {
        viewModelScope.launch {
            val updatedList = _state.value.basketList.map { item ->
                if (item == basketItem && item.quantity > 1) item.copy(quantity = item.quantity - 1)
                else item
            }
            _state.value = _state.value.copy(basketList = updatedList)
            updateTotalPrice()
        }
    }

    fun updateTotalPrice() {
        val totalPrice = _state.value.basketList.sumOf { it.productPrice * it.quantity }
        _state.value = _state.value.copy(totalPrice = totalPrice)
    }

    fun calculateTotalPrice(): Int {
        return state.value.basketList.sumOf { it.productPrice * it.quantity }
    }


}