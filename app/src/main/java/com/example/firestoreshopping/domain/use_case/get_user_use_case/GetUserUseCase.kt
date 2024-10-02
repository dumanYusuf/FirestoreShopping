package com.example.firestoreshopping.domain.use_case.get_user_use_case

import com.example.firestoreshopping.domain.model.Users
import com.example.firestoreshopping.domain.repo.FirestoreRepo
import com.example.firestoreshopping.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repo: FirestoreRepo) {

    suspend fun getUser(): Flow<Resource<List<Users>>>{
        return repo.getUser()
    }

}