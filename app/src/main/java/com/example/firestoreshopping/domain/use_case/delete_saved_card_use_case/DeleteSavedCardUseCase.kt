package com.example.firestoreshopping.domain.use_case.delete_saved_card_use_case

import com.example.firestoreshopping.domain.model.Card
import com.example.firestoreshopping.domain.repo.FirestoreRepo
import com.example.firestoreshopping.util.Resource
import javax.inject.Inject

class DeleteSavedCardUseCase @Inject constructor(private val repo: FirestoreRepo) {


    suspend fun deleteCard(card: Card):Resource<Card>{
        return repo.deleteCard(card)
    }

}