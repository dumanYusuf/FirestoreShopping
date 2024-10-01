package com.example.firestoreshopping.domain.use_case.delete_products_use_case

import com.example.firestoreshopping.domain.model.Favori
import com.example.firestoreshopping.domain.model.Products
import com.example.firestoreshopping.domain.repo.FirestoreRepo
import com.example.firestoreshopping.util.Resource
import javax.inject.Inject

class DeleteProductsUseCase @Inject constructor(private val repo: FirestoreRepo) {

    suspend fun deleteProducts(id: Favori):Resource<Favori>{
        return repo.deleteProducts(id)
    }

}