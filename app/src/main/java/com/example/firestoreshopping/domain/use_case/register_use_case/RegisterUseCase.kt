package com.example.firestoreshopping.domain.use_case.register_use_case

import com.example.firestoreshopping.domain.model.Users
import com.example.firestoreshopping.domain.repo.AuthRepo
import com.example.firestoreshopping.util.Resource
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repo: AuthRepo) {


    suspend fun registerUser(users: Users,password:String):Resource<Users>{
        return repo.register(users,password)
    }

}