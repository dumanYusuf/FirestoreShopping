package com.example.firestoreshopping.domain.use_case.get_favori_use_case

import com.example.firestoreshopping.domain.model.Favori
import com.example.firestoreshopping.domain.repo.FirestoreRepo
import com.example.firestoreshopping.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriUseCase @Inject constructor(private val repo: FirestoreRepo) {


    suspend fun getFavori():Flow<Resource<List<Favori>>>{
        return repo.getProductToFavori()
    }

}