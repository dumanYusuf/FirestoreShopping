package com.example.firestoreshopping.data.repo

import android.util.Log
import com.example.firestoreshopping.domain.model.Category
import com.example.firestoreshopping.domain.model.Products
import com.example.firestoreshopping.domain.repo.FirestoreRepo
import com.example.firestoreshopping.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import okhttp3.internal.filterList
import java.util.Locale
import javax.inject.Inject

class FirestoreRepoImpl @Inject constructor(
    private val firestore:FirebaseFirestore
):FirestoreRepo {

    override suspend fun getCategory(): Flow<Resource<List<Category>>> = flow{
        try {
           val result=firestore.collection("Category").get().await()
            val category=result.documents.map {
                it.toObject(Category::class.java)!!.copy(categoryId = it.id)
            }
            emit(Resource.Success(category))
        }
        catch (e:Exception){
            emit(Resource.Error("Error"))
            Log.e("error","erroro:${e.localizedMessage}")
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

    override suspend fun productSearch(search: String,categoryId: String): Flow<Resource<List<Products>>> = flow {
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


}