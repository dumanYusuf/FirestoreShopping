package com.example.firestoreshopping.domain.use_case.get_saved_card_use_case

import com.example.firestoreshopping.domain.model.Card
import com.example.firestoreshopping.domain.repo.FirestoreRepo
import com.example.firestoreshopping.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedCardUseCase @Inject constructor(private val repo: FirestoreRepo) {

    suspend operator fun invoke():Flow<Resource<List<Card>>>{
        return repo.getSavedCard()
    }

}