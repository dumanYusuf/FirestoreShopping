package com.example.firestoreshopping.domain.use_case.delete_basket_use_case

import com.example.firestoreshopping.domain.model.Basket
import com.example.firestoreshopping.domain.repo.FirestoreRepo
import com.example.firestoreshopping.util.Resource
import javax.inject.Inject

class DeleteBasketUseCase @Inject constructor(private val repo: FirestoreRepo) {

    suspend fun deleteBasket(id:Basket):Resource<Basket>{
        return repo.deleteBasketProduct(id)
    }

}