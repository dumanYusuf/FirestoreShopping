package com.example.firestoreshopping.domain.repo

import com.example.firestoreshopping.domain.model.Users
import com.example.firestoreshopping.util.Resource

interface AuthRepo {


    suspend fun register(users: Users,password:String):Resource<Users>
    suspend fun login(email:String,password: String):Resource<Users>
     fun logOut()

}