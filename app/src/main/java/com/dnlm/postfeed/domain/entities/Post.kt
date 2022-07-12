package com.dnlm.postfeed.domain.entities

import com.dnlm.postfeed.utils.Constants
import com.dnlm.postfeed.utils.extensions.sha256
import com.dnlm.posts_client.models.AuthorResponse
import com.dnlm.posts_client.models.CommentResponse
import java.io.Serializable

data class Post (
    val id: Int,
    val title: String,
    val body: String,
    var author: AuthorResponse? = null,
    var comments: List<CommentResponse>? = null
): Serializable {
    fun imgURL(): String {
        val seed: String = title.sha256()
        return "${Constants.IMG_URL}$seed/${Constants.IMG_SIZE}/${Constants.IMG_SIZE}"
    }
}
