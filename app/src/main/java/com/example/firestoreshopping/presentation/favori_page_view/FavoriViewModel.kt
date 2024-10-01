package com.example.firestoreshopping.presentation.favori_page_view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestoreshopping.domain.use_case.add_favori_firestore_use_case.AddFavoriFirestoreUseCase
import com.example.firestoreshopping.domain.use_case.get_favori_use_case.GetFavoriUseCase
import com.example.firestoreshopping.presentation.home_page_view.HomePageState
import com.example.firestoreshopping.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriViewModel @Inject constructor(
    private val getFavoriUseCase: GetFavoriUseCase
):ViewModel() {

    private val _state= MutableStateFlow<FavoriState>(FavoriState())
    val state:StateFlow<FavoriState> = _state



    fun loadFavori(){
        viewModelScope.launch {
            _state.value= FavoriState(isLoading = true)
            getFavoriUseCase.getFavori().collect{result->
                when(result){
                    is Resource.Success->{
                        _state.value= FavoriState(favoriList = result.data?: emptyList())
                        Log.e("success","favori loadd success")
                    }
                    is Resource.Error -> {
                        _state.value = FavoriState(isErorr = result.message ?: "An unknown error occurred")
                        Log.e("unsuccess", "favori not load: ${result.message}")
                    }

                    is Resource.Loading->{
                        _state.value= FavoriState(isLoading = true)
                        Log.e("loading","favori loadding")
                    }
                }
            }
        }
    }


}