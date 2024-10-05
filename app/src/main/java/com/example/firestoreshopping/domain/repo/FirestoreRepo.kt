package com.example.firestoreshopping.domain.repo

import com.example.firestoreshopping.domain.model.Basket
import com.example.firestoreshopping.domain.model.Card
import com.example.firestoreshopping.domain.model.Category
import com.example.firestoreshopping.domain.model.Favori
import com.example.firestoreshopping.domain.model.LocationUser
import com.example.firestoreshopping.domain.model.Products
import com.example.firestoreshopping.domain.model.Users
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
    suspend fun deleteBasketProduct(id: Basket):Resource<Basket>
    suspend fun getUser():Flow<Resource<List<Users>>>
    suspend fun addLocation(location: LocationUser):Resource<LocationUser>
    suspend fun getLocation():Flow<Resource<List<LocationUser>>>
    suspend fun deleteLocation(id:LocationUser):Resource<LocationUser>
    suspend fun addCard(card:Card):Resource<Card>
    suspend fun getSavedCard():Flow<Resource<List<Card>>>


    }