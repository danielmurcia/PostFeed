package com.dnlm.postfeed.presentation.posts

import com.dnlm.postfeed.presentation.posts.viewmodels.PostFeedViewModel
import com.dnlm.postfeed.presentation.posts.views.PostItem
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dnlm.postfeed.R
import com.dnlm.postfeed.domain.entities.Post
import com.dnlm.postfeed.presentation.post_detail.PostDetailFragment

class PostFeedFragment : Fragment(), PostItem.Listener {

    private lateinit var postFeedViewModel: PostFeedViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        postFeedViewModel =
            ViewModelProvider(this)[PostFeedViewModel::class.java]

        val root = inflater.inflate(R.layout.fragment_posts, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.rv_main)
        val adapter: PostFeedAdapter = PostFeedAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        postFeedViewModel.posts.observe(viewLifecycleOwner, Observer {
            adapter.posts = ArrayList(it)
        })

        return root
    }

    override fun onSelectPost(post: Post) {
        findNavController().navigate(R.id.action_post_selected, bundleOf(Pair(PostDetailFragment.POST_ARG, post)))
    }
}