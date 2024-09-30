package com.example.firestoreshopping.domain.use_case.log_out_use_case

import com.example.firestoreshopping.domain.repo.AuthRepo
import javax.inject.Inject

class LogOutUseCase @Inject constructor(private val repo: AuthRepo) {

     fun logOut(){
        repo.logOut()
    }

}