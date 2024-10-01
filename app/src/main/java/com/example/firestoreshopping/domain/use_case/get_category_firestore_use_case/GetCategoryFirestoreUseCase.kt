package com.example.firestoreshopping.domain.use_case.get_category_firestore_use_case

import com.example.firestoreshopping.domain.model.Category
import com.example.firestoreshopping.domain.repo.FirestoreRepo
import com.example.firestoreshopping.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryFirestoreUseCase @Inject constructor(private val repo: FirestoreRepo) {


    suspend fun getCategory():Flow<Resource<List<Category>>>{
        return repo.getCategory()
    }

}