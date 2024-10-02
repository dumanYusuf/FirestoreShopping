package com.example.firestoreshopping.domain.use_case.get_basktet_use_case

import com.example.firestoreshopping.domain.model.Basket
import com.example.firestoreshopping.domain.repo.FirestoreRepo
import com.example.firestoreshopping.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBasketUseCase @Inject constructor(private val repo: FirestoreRepo) {

    suspend fun getBasket():Flow<Resource<List<Basket>>>{
        return repo.getProductToBasket()
    }

}