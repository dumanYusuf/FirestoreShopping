package com.example.firestoreshopping.domain.use_case.delete_location_use_case

import com.example.firestoreshopping.domain.model.LocationUser
import com.example.firestoreshopping.domain.repo.FirestoreRepo
import com.example.firestoreshopping.util.Resource
import javax.inject.Inject

class DeleteLocationUseCase @Inject constructor(private val repo: FirestoreRepo) {

    suspend fun deleteLocation(id:LocationUser):Resource<LocationUser>{
        return repo.deleteLocation(id)
    }

}