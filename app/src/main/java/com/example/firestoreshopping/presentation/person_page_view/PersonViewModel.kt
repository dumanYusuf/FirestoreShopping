package com.example.firestoreshopping.presentation.person_page_view

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.firestoreshopping.domain.use_case.log_out_use_case.LogOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PersonViewModel @Inject constructor(private val logOutUseCase: LogOutUseCase):ViewModel(){


    fun logOut(){
        logOutUseCase.logOut()
        Log.e("success","log out success")
    }

}