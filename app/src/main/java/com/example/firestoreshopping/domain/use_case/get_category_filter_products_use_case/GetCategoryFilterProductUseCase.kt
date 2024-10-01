package com.example.firestoreshopping.domain.use_case.get_category_filter_products_use_case

import com.example.firestoreshopping.domain.model.Products
import com.example.firestoreshopping.domain.repo.FirestoreRepo
import com.example.firestoreshopping.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryFilterProductUseCase @Inject constructor(private val repo: FirestoreRepo) {

    suspend fun getFilterProduct(categoryId:String): Flow<Resource<List<Products>>>{
        return repo.getProducts(categoryId)
    }

}