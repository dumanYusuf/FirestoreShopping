package com.example.firestoreshopping.presentation.location_page_view.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestoreshopping.domain.model.LocationUser
import com.example.firestoreshopping.domain.use_case.add_location_use_case.AddLocationUseCase
import com.example.firestoreshopping.presentation.location_page_view.LocationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LocationViewModel @Inject constructor(private val locationUseCase: AddLocationUseCase):ViewModel(){


    private val _state= MutableStateFlow<LocationState>(LocationState())
    val state:StateFlow<LocationState> = _state

    fun addLocation(location:LocationUser){
        viewModelScope.launch {
            locationUseCase.addLocation(location)
            Log.e("location success","location success firestore")
        }
    }

}
