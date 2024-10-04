package com.example.firestoreshopping.domain.use_case.add_location_use_case

import com.example.firestoreshopping.domain.model.LocationUser
import com.example.firestoreshopping.domain.repo.FirestoreRepo
import com.example.firestoreshopping.util.Resource
import javax.inject.Inject


class AddLocationUseCase @Inject constructor(private val repo: FirestoreRepo) {

    suspend fun addLocation(location: LocationUser):Resource<LocationUser>{
        return repo.addLocation(location)
    }

}