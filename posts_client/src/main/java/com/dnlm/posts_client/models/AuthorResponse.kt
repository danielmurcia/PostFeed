package com.dnlm.posts_client.models

data class AuthorResponse (
    val id: Int,
    val name: String,
    val email: String,
    val website: String,
    val phone: String,
    val address: AddressResponse
)