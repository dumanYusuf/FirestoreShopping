package com.example.firestoreshopping.domain.model

data class Favori(
    val favoriId:String="",
    val productId: String = "",
    val productName: String = "",
    val productPrice: Int = 0,
    val productImage: String = "",
    val isFavorited:Boolean=false,
) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "favoriId" to favoriId,
            "productId" to productId,
            "productName" to productName,
            "productPrice" to productPrice,
            "productImage" to productImage,
            "isFavorited" to isFavorited
        )
    }
}
