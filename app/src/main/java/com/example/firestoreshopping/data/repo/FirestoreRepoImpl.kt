package com.example.firestoreshopping.data.repo

import android.util.Log
import com.example.firestoreshopping.domain.model.Category
import com.example.firestoreshopping.domain.repo.FirestoreRepo
import com.example.firestoreshopping.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
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
}