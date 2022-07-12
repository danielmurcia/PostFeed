package com.dnlm.posts_client.models
data class CommentResponse (
    val id: Int,
    val postId: Int,
    val name: String,
    val email: String,
    val body: String
)