package com.example.firestoreshopping.presentation.favori_page_view

import androidx.lifecycle.ViewModel
import com.example.firestoreshopping.domain.use_case.add_favori_firestore_use_case.AddFavoriFirestoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class FavoriViewModel @Inject constructor():ViewModel() {

    private val _state= MutableStateFlow<FavoriState>(FavoriState())
    val state:StateFlow<FavoriState> = _state




}