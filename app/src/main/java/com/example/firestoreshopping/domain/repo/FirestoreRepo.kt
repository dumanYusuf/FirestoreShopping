package com.example.firestoreshopping.domain.repo

import com.example.firestoreshopping.domain.model.Category
import com.example.firestoreshopping.domain.model.Products
import com.example.firestoreshopping.util.Resource
import kotlinx.coroutines.flow.Flow



interface FirestoreRepo {


    suspend fun getCategory():Flow<Resource<List<Category>>>
    suspend fun getProducts(categoryId:String):Flow<Resource<List<Products>>>

}