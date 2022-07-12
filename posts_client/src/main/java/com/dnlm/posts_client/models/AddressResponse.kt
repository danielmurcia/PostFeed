package com.dnlm.posts_client.models

data class AddressResponse(
    val street: String?,
    val city: String?,
    val email: String?,
    val zipcode: String?,
    val geo: GeoLocationResponse?
)
