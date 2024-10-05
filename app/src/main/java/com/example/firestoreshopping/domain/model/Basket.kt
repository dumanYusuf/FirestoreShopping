package com.example.firestoreshopping.domain.model

data class Basket(
    val basketId:String="",
    val productId: String = "",
    val productName: String = "",
    val productPrice: Int = 0,
    val productImage: String = "",
    val isBasket:Boolean=false,
    var quantity: Int = 1,
    var totalPrice: Int = productPrice,

) {

    fun toMap(): Map<String, Any> {
        return mapOf(
            "basketId" to basketId,
            "productId" to productId,
            "productName" to productName,
            "productPrice" to productPrice,
            "productImage" to productImage,
            "isBasket" to isBasket,
            "quantity" to quantity,
            "totalPrice" to totalPrice,
        )
    }
}