package com.dnlm.postfeed.presentation.post_detail.views

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dnlm.postfeed.R
import com.dnlm.posts_client.models.CommentResponse

class CommentItem(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)) {

    fun onBind(model: CommentResponse) {
        itemView.findViewById<TextView>(R.id.tv_name).text = model.name
        itemView.findViewById<TextView>(R.id.tv_email).text = model.email
        itemView.findViewById<TextView>(R.id.tv_comment).text = model.body
    }
}