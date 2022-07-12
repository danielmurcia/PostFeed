package com.dnlm.postfeed.presentation.post_detail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnlm.postfeed.presentation.post_detail.views.CommentItem
import com.dnlm.posts_client.models.CommentResponse

class PostCommentsAdapter() : RecyclerView.Adapter<CommentItem>() {

    var comments = listOf<CommentResponse>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = comments.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentItem {
        return CommentItem(parent)
    }

    override fun onBindViewHolder(holder: CommentItem, position: Int) {
        holder.onBind(comments[position])
    }

}