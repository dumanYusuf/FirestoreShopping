package com.example.firestoreshopping.domain.use_case.get_location_use_case

import com.example.firestoreshopping.domain.model.LocationUser
import com.example.firestoreshopping.domain.repo.FirestoreRepo
import com.example.firestoreshopping.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(private val repo:FirestoreRepo) {

     suspend fun getLocation():Flow<Resource<List<LocationUser>>>{
         return repo.getLocation()
     }
}