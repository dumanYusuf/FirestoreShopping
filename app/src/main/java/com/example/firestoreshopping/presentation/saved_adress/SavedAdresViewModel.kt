package com.example.firestoreshopping.presentation.saved_adress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firestoreshopping.domain.use_case.get_location_use_case.GetLocationUseCase
import com.example.firestoreshopping.presentation.saved_card_page_view.SavedState
import com.example.firestoreshopping.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SavedAdresViewModel @Inject constructor(
    private val getLocationUseCase: GetLocationUseCase
):ViewModel(){


    private val _state= MutableStateFlow<SavedAdressState>(SavedAdressState())
    val state:StateFlow<SavedAdressState> =_state

    fun loadAdres(){
        viewModelScope.launch {
            _state.value=SavedAdressState(isLoading = true)
            getLocationUseCase.getLocation().collect{
                when(it){
                    is Resource.Success->{
                        _state.value=SavedAdressState(adresList = it.data?: emptyList())
                    }
                    is Resource.Error->{
                        _state.value=SavedAdressState(isError = "Error")
                    }
                    is Resource.Loading->{
                        _state.value=SavedAdressState(isLoading = true)
                    }
                }
            }
        }
    }

}