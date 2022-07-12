package com.dnlm.postfeed.presentation.posts.views

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dnlm.postfeed.R
import com.dnlm.postfeed.domain.entities.Post

class PostItem(parent: ViewGroup, private val listener: Listener) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.post_feed_item, parent, false)) {

    fun onBind(model: Post) {
        itemView.findViewById<TextView>(R.id.tv_title).text = model.title
        itemView.findViewById<TextView>(R.id.tv_body).text = model.body
        itemView.findViewById<TextView>(R.id.tv_author).text =
            if(model.author == null) "" else "by ${model.author!!.name}"
        itemView.findViewById<TextView>(R.id.tv_comments).text =
            if(model.comments.isNullOrEmpty()) "" else "${model.comments!!.size} comments"

        itemView.setOnClickListener { listener.onSelectPost(model) }
    }

    interface Listener {
        fun onSelectPost(post: Post)
    }
}