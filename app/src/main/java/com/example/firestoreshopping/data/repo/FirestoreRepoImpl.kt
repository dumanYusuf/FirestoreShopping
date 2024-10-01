package com.example.firestoreshopping.data.repo

import android.util.Log
import com.example.firestoreshopping.domain.model.Category
import com.example.firestoreshopping.domain.model.Favori
import com.example.firestoreshopping.domain.model.Products
import com.example.firestoreshopping.domain.repo.FirestoreRepo
import com.example.firestoreshopping.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import okhttp3.internal.filterList
import java.util.Locale
import javax.inject.Inject

class FirestoreRepoImpl @Inject constructor(
    private val firestore:FirebaseFirestore,
    private val auth:FirebaseAuth
):FirestoreRepo {

    override suspend fun getCategory(): Flow<Resource<List<Category>>> = flow {
        try {
            val result = firestore.collection("Category").get().await()
            val category = result.documents.map {
                it.toObject(Category::class.java)!!.copy(categoryId = it.id)
            }
            emit(Resource.Success(category))
        } catch (e: Exception) {
            emit(Resource.Error("Error"))
            Log.e("error", "erroro:${e.localizedMessage}")
        }
    }

    override suspend fun getProducts(categoryId: String): Flow<Resource<List<Products>>> = flow {
        try {
            val result = firestore.collection("Category")
                .document(categoryId)
                .collection("Products").get().await()

            val products = result.documents.mapNotNull {
                it.toObject(Products::class.java)?.copy(productId = it.id)
            }
            emit(Resource.Success(products))
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.localizedMessage}"))
            Log.e("error", "error: ${e.localizedMessage}")
        }
    }

    override suspend fun productSearch(
        search: String,
        categoryId: String
    ): Flow<Resource<List<Products>>> = flow {
        try {
            emit(Resource.Loading())
            val productDocRef = firestore.collection("Category").document(categoryId)
                .collection("Products").get().await()

            val searchList = productDocRef.documents.mapNotNull { document ->
                document.toObject(Products::class.java)?.takeIf { product ->
                    product.productName.contains(search, ignoreCase = true)
                }
            }
            emit(Resource.Success(searchList))
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.message}"))
        }
    }

    override suspend fun addProductToFavori(products: Favori): Resource<Favori> {
        return try {
            val userId = auth.currentUser?.uid
            if (userId != null) {
                firestore.collection("Users").document(userId)
                    .collection("Favori").add(products.toMap()).await()
                Resource.Success(products)
            } else {
                Resource.Error("User ID is null")
            }
        } catch (e: Exception) {
            Resource.Error("Error: ${e.message}")
        }
    }

    override suspend fun getProductToFavori(): Flow<Resource<List<Favori>>> = flow{
        try {
            val userId=auth.currentUser?.uid
            if (userId!=null){
                val favoriDocRef=firestore.collection("Users").document(userId)
                    .collection("Favori").get().await()
                val favoriList=favoriDocRef.documents.mapNotNull {
                    it.toObject(Favori::class.java)
                }
                emit(Resource.Success(favoriList))
            }
        }
        catch (e:Exception){
            emit(Resource.Error("Error"))
        }
    }

}
