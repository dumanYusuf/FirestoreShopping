package com.example.firestoreshopping.presentation.home_page_view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestoreshopping.domain.use_case.get_category_firestore_use_case.GetCategoryFirestoreUseCase
import com.example.firestoreshopping.domain.use_case.get_user_use_case.GetUserUseCase
import com.example.firestoreshopping.presentation.person_page_view.PersonState
import com.example.firestoreshopping.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val getCategoryFirestoreUseCase: GetCategoryFirestoreUseCase,
    private val getUserUseCase: GetUserUseCase
):
    ViewModel() {

        private val _state= MutableStateFlow<HomePageState>(HomePageState())
        val state:StateFlow<HomePageState> = _state

    private val _stateUser= MutableStateFlow<PersonState>(PersonState())
    val stateUser:StateFlow<PersonState> = _stateUser

    init {
        getUser()
    }

    fun loadCategory(){
        viewModelScope.launch {
            _state.value=HomePageState(isLoading = true)
        getCategoryFirestoreUseCase.getCategory().collect{result->
            when(result){
                is Resource.Success->{
                    _state.value=HomePageState(categoryList = result.data?: emptyList())
                    Log.e("success","category loadd success")
                }
                is Resource.Error -> {
                    _state.value = HomePageState(isErorr = result.message ?: "An unknown error occurred")
                    Log.e("unsuccess", "category not load: ${result.message}")
                }

                is Resource.Loading->{
                    _state.value=HomePageState(isLoading = true)
                    Log.e("loading","category loadding")
                }
            }
        }
        }
    }

    fun getUser(){
        viewModelScope.launch {
            getUserUseCase.getUser().collect{
                _stateUser.value= PersonState(userList = it.data?: emptyList())
                Log.e("home get user","home get user success")
            }
        }
    }



}