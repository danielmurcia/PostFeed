package com.dnlm.posts_client.models

data class PostResponse (
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)