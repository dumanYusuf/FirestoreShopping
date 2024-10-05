package com.example.firestoreshopping.domain.model

data class Card(
    val cardId:String="",
    val cardNumber: String = "",
    val cardYearsMonth: String = "",
    val cardCv: String = "",
    val cardHolder: String = ""
) {
    // toMap function
    fun toMap(): Map<String, Any> {
        return mapOf(
            "cardId" to cardId,
            "cardNumber" to cardNumber,
            "cardYearsMonth" to cardYearsMonth,
            "cardCv" to cardCv,
            "cardHolder" to cardHolder
        )
    }
}
