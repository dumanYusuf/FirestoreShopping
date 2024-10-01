package com.example.firestoreshopping.domain.use_case.add_favori_firestore_use_case

import com.example.firestoreshopping.domain.model.Favori
import com.example.firestoreshopping.domain.repo.FirestoreRepo
import com.example.firestoreshopping.util.Resource
import javax.inject.Inject

class AddFavoriFirestoreUseCase @Inject constructor(private val repo: FirestoreRepo) {


    suspend fun addFavori(products:Favori):Resource<Favori>{
        return repo.addProductToFavori(products)
    }

}