package com.example.firestoreshopping.presentation.location_page_view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestoreshopping.domain.model.LocationUser
import com.example.firestoreshopping.domain.use_case.add_location_use_case.AddLocationUseCase
import com.example.firestoreshopping.domain.use_case.get_location_use_case.GetLocationUseCase
import com.example.firestoreshopping.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LocationViewModel @Inject constructor(
    private val locationUseCase: AddLocationUseCase,
    private val getLocationUseCase: GetLocationUseCase
):ViewModel(){


    private val _state= MutableStateFlow<LocationState>(LocationState())
    val state:StateFlow<LocationState> = _state

    fun addLocation(location:LocationUser){
        viewModelScope.launch {
            locationUseCase.addLocation(location)
            Log.e("location success","location success firestore")
        }
    }

    fun loadLocation(){
        viewModelScope.launch {
            _state.value=LocationState(isLoading = true)
            getLocationUseCase.getLocation().collect{
               when(it){
                  is Resource.Success->{
                      _state.value=LocationState(it.data?: emptyList())
                      Log.e("success location","get location success")
                  }
                   is Resource.Error->{
                       _state.value=LocationState(isError = "Error")
                       Log.e("unsuccess location","get location unsuccess:${it.message}")
                   }
                   is Resource.Loading->{
                       _state.value=LocationState(isLoading = true)
                       Log.e(" loading","get location loading")
                   }
               }
            }
        }


    }

}
