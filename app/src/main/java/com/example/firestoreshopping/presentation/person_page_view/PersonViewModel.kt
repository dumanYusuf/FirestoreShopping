package com.example.firestoreshopping.presentation.person_page_view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestoreshopping.domain.use_case.get_user_use_case.GetUserUseCase
import com.example.firestoreshopping.domain.use_case.log_out_use_case.LogOutUseCase
import com.example.firestoreshopping.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PersonViewModel @Inject constructor(
    private val logOutUseCase: LogOutUseCase,
    private val getUserUseCase: GetUserUseCase
):ViewModel(){

    private val _state= MutableStateFlow<PersonState>(PersonState())
    val state: StateFlow<PersonState> = _state

    fun logOut(){
        logOutUseCase.logOut()
        Log.e("success","log out success")
    }

    fun getUser(){
        viewModelScope.launch {
            _state.value= PersonState(isLoading = true)
            getUserUseCase.getUser().collect{
                when(it){
                    is Resource.Success->{
                        _state.value= PersonState(userList = it.data?: emptyList())
                        Log.e("success","user success:${it.data}")
                    }
                    is Resource.Loading->{
                        _state.value= PersonState(isLoading = true)
                        Log.e("loading user","user loading")
                    }
                    is Resource.Error->{
                        _state.value= PersonState(isErorr = "Error")
                        Log.e("error user","user error:${it.message}")
                    }
                }
            }
        }
    }


}