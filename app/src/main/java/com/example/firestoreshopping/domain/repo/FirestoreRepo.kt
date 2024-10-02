package com.example.firestoreshopping.domain.repo

import com.example.firestoreshopping.domain.model.Basket
import com.example.firestoreshopping.domain.model.Category
import com.example.firestoreshopping.domain.model.Favori
import com.example.firestoreshopping.domain.model.Products
import com.example.firestoreshopping.util.Resource
import kotlinx.coroutines.flow.Flow



interface FirestoreRepo {


    suspend fun getCategory():Flow<Resource<List<Category>>>
    suspend fun getProducts(categoryId:String):Flow<Resource<List<Products>>>
    suspend fun productSearch(search:String,categoryId:String):Flow<Resource<List<Products>>>
    suspend fun addProductToFavori(products: Favori):Resource<Favori>
    suspend fun getProductToFavori():Flow<Resource<List<Favori>>>
    suspend fun deleteProducts(id:Favori):Resource<Favori>
    suspend fun addProductToBasket(product:Basket):Resource<Basket>
    suspend fun getProductToBasket():Flow<Resource<List<Basket>>>


}