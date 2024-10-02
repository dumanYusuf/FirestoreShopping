package com.example.firestoreshopping.domain.use_case.add_basket_firestore_use_case

import com.example.firestoreshopping.domain.model.Basket
import com.example.firestoreshopping.domain.repo.FirestoreRepo
import com.example.firestoreshopping.util.Resource
import javax.inject.Inject

class AddBasketFirestoreUseCase @Inject constructor(private val repo: FirestoreRepo) {


    suspend fun addBasket(product:Basket):Resource<Basket>{
        return repo.addProductToBasket(product)
    }

}