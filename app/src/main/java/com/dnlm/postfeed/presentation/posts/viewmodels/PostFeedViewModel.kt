package com.dnlm.postfeed.presentation.posts.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnlm.postfeed.domain.entities.Post
import com.dnlm.postfeed.domain.usecases.LoadPostsUseCase
import com.dnlm.posts_client.PostsFeedsRepository
import kotlinx.coroutines.launch

class PostFeedViewModel : ViewModel() {

    // TODO This should be injected with an injection tool (Dagger or Koin, maybe?)
    private val useCase = LoadPostsUseCase(PostsFeedsRepository)

    val posts: LiveData<MutableList<Post>> = useCase.posts

    init {
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope.launch {
            useCase.call()
        }
    }
}