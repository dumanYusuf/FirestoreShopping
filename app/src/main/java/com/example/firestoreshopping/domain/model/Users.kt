package com.example.firestoreshopping.domain.model

data class Users(
    val userId: String,
    val userName: String,
    val userLastName: String,
    val userMail: String,
    val userProfilUrl: String
) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "userId" to userId,
            "userName" to userName,
            "userLastName" to userLastName,
            "userMail" to userMail,
            "userProfilUrl" to userProfilUrl
        )
    }
}
