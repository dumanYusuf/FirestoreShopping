package com.example.firestoreshopping.domain.model

data class LocationUser(
    val locationId:String="",
    val province: String = "",
    val country: String = "",
    val neighborhood: String = "",
    val streetNo:String="",
    val floorNo:String=""
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "locationId" to locationId,
            "province" to province,
            "country" to country,
            "neighborhood" to neighborhood,
            "streetNo" to streetNo,
            "floorNo" to floorNo
        )
    }
}
