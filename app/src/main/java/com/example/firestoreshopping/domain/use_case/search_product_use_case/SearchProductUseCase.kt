package com.example.firestoreshopping.domain.use_case.search_product_use_case

import com.example.firestoreshopping.domain.model.Products
import com.example.firestoreshopping.domain.repo.FirestoreRepo
import com.example.firestoreshopping.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchProductUseCase @Inject constructor(private val repo: FirestoreRepo) {

    suspend fun searchProduct(search:String,categoryId:String):Flow<Resource<List<Products>>>{
        return repo.productSearch(search,categoryId)
    }

}