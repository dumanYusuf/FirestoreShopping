package com.example.firestoreshopping.domain.use_case.login_use_case

import com.example.firestoreshopping.domain.model.Users
import com.example.firestoreshopping.domain.repo.AuthRepo
import com.example.firestoreshopping.util.Resource
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repo: AuthRepo) {

    suspend operator fun invoke(email:String,password:String):Resource<Users>{
        return repo.login(email,password)
    }

}