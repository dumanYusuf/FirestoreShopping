package com.example.firestoreshopping.presentation.product_page_view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestoreshopping.domain.use_case.get_category_filter_products_use_case.GetCategoryFilterProductUseCase
import com.example.firestoreshopping.domain.use_case.search_product_use_case.SearchProductUseCase
import com.example.firestoreshopping.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productUseCase: GetCategoryFilterProductUseCase,
    private val searchUseCase:SearchProductUseCase,
)
    :ViewModel(){

    private val _state= MutableStateFlow<ProductState>(ProductState())
    val state: StateFlow<ProductState> = _state

   private val _stateSearch= MutableStateFlow<SearchState>(SearchState())
    val stateSearch: StateFlow<SearchState> = _stateSearch


    fun loadCategoryFilterProduct(categoryId:String){
        viewModelScope.launch {
            _state.value=ProductState(isLoading = true)
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

    fun searchProduct(search:String,categoryId: String){
        viewModelScope.launch {
            _stateSearch.value= SearchState(isLoading = true)
            searchUseCase.searchProduct(search,categoryId).collect{result->
                when(result){
                    is Resource.Success->{
                        _stateSearch.value= SearchState(productList = result.data?: emptyList())
                        Log.e("success","search  product  success:${result.data}")
                    }
                    is Resource.Error -> {
                        _stateSearch.value = SearchState(isError = result.message ?: "An unknown error occurred")
                        Log.e("unsuccess", "search  product not search: ${result.message}")
                    }

                    is Resource.Loading->{
                        _stateSearch.value= SearchState(isLoading = true)
                        Log.e("loading","search  product loadding")
                    }
                }
            }
        }
    }
}