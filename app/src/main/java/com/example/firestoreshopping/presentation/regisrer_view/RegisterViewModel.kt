package com.example.firestoreshopping.presentation.regisrer_view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestoreshopping.domain.model.Users
import com.example.firestoreshopping.domain.use_case.register_use_case.RegisterUseCase
import com.example.firestoreshopping.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCase):ViewModel(){

    private val _state= MutableStateFlow<RegisterState>(RegisterState())
    val state:StateFlow<RegisterState> = _state

    fun register(users: Users,password:String){
        viewModelScope.launch {
            _state.value= RegisterState(isLoading = true)
           val result= registerUseCase.registerUser(users,password)
            when(result){
                is Resource.Success->{
                    _state.value=RegisterState(isSuccess = true)
                    Log.e("success","register user success")
                }
                is Resource.Error->{
                    _state.value=RegisterState(isError = "Error")
                    Log.e("failure","register user failure")
                }
                is Resource.Loading->{
                    _state.value=RegisterState(isLoading = true)
                    Log.e("loading","register user loading")
                }
            }
        }
    }

}