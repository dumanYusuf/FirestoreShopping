package com.example.firestoreshopping.presentation.product_page_view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestoreshopping.domain.use_case.get_category_filter_products_use_case.GetCategoryFilterProductUseCase
import com.example.firestoreshopping.presentation.home_page_view.HomePageState
import com.example.firestoreshopping.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductsViewModel @Inject constructor(private val productUseCase: GetCategoryFilterProductUseCase)
    :ViewModel(){

    private val _state= MutableStateFlow<ProductState>(ProductState())
    val state: StateFlow<ProductState> = _state


    fun loadCategoryFilterProduct(categoryId:String){
        viewModelScope.launch {
            productUseCase.getFilterProduct(categoryId).collect{result->
                when(result){
                    is Resource.Success->{
                        _state.value= ProductState(productList = result.data?: emptyList())
                        Log.e("success","category filter product loadd success")
                    }
                    is Resource.Error -> {
                        _state.value = ProductState(isError = result.message ?: "An unknown error occurred")
                        Log.e("unsuccess", "category filter product not load: ${result.message}")
                    }

                    is Resource.Loading->{
                        _state.value= ProductState(isLoading = true)
                        Log.e("loading","category filter product loadding")
                    }
                }
            }
        }
    }
}