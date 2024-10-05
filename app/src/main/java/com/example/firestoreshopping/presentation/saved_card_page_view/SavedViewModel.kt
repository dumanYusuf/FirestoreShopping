package com.example.firestoreshopping.presentation.saved_card_page_view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestoreshopping.domain.use_case.get_saved_card_use_case.GetSavedCardUseCase
import com.example.firestoreshopping.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SavedViewModel @Inject constructor(private val getSavedCardUseCase: GetSavedCardUseCase):
    ViewModel(){


        private val _state= MutableStateFlow<SavedState>(SavedState())
        val state:StateFlow<SavedState> = _state

       fun loadSavedCard(){
           viewModelScope.launch {
               _state.value=SavedState(isLoading = true)
               getSavedCardUseCase.invoke().collect{savedCard->
                   when(savedCard){
                       is Resource.Success->{
                           _state.value=SavedState(savedCardList = savedCard.data?: emptyList())
                           Log.e("savedCard","Success SavedCard ")
                       }
                       is Resource.Loading->{
                           _state.value=SavedState(isLoading = true)
                           Log.e("savedCard","loading SavedCard ")
                       }
                       is Resource.Error->{
                           _state.value=SavedState(isError = "Error")
                           Log.e("savedCard","UnSuccess SavedCard:${savedCard.message}")
                       }
                   }
               }
           }
       }

}