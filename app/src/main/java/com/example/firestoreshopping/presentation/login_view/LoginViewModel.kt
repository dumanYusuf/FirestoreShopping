package com.example.firestoreshopping.presentation.login_view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestoreshopping.domain.use_case.login_use_case.LoginUseCase
import com.example.firestoreshopping.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel  @Inject constructor(private val loginUseCase: LoginUseCase):ViewModel(){

    private val _state= MutableStateFlow<LoginState>(LoginState())
    val state: StateFlow<LoginState> = _state

    fun login(email:String, password:String){
        viewModelScope.launch {
            _state.value= LoginState(isLoading = true)
            val result= loginUseCase.invoke(email,password)
            when(result){
                is Resource.Success->{
                    _state.value= LoginState(isSuccess = true)
                    Log.e("success","login user success")
                }
                is Resource.Error->{
                    _state.value= LoginState(isError = "Error")
                    Log.e("failure","login user failure")
                }
                is Resource.Loading->{
                    _state.value= LoginState(isLoading = true)
                    Log.e("loading","login user loading")
                }
            }
        }
    }

}