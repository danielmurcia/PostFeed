package com.dnlm.postfeed.presentation.posts

import com.dnlm.postfeed.presentation.posts.views.PostItem
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnlm.postfeed.domain.entities.Post

class PostFeedAdapter(private val itemListener: PostItem.Listener) : RecyclerView.Adapter<PostItem>() {

    var posts = arrayListOf<Post>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = posts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItem {
        return PostItem(parent, itemListener)
    }

    override fun onBindViewHolder(holder: PostItem, position: Int) {
        holder.onBind(posts[position])
    }

}