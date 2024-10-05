package com.example.firestoreshopping.domain.use_case.add_to_card_firestore_use_case

import com.example.firestoreshopping.domain.model.Card
import com.example.firestoreshopping.domain.repo.FirestoreRepo
import com.example.firestoreshopping.util.Resource
import javax.inject.Inject

class AddCardUseCase @Inject constructor(private val repo: FirestoreRepo) {

    suspend fun addCard(card:Card):Resource<Card>{
        return repo.addCard(card)
    }

}