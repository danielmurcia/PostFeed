package com.dnlm.postfeed.domain.usecases

import androidx.lifecycle.MutableLiveData
import com.dnlm.postfeed.domain.entities.Post
import com.dnlm.posts_client.PostsFeedsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.lang.Exception

class LoadPostsUseCase(repository: PostsFeedsRepository) {

    val posts = MutableLiveData<MutableList<Post>>()
    private val apiClient = repository.ApiClient()

    private val postsPerPage: Int = 10
    private var page: Int = 0

    init {
        posts.value = mutableListOf()
    }

    suspend fun call() {
        coroutineScope {
            val deferred = async(Dispatchers.IO) {
                page++
                apiClient.getPosts(page, postsPerPage)
            }
            val response = deferred.await()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val newPosts = response.body()!!
                        newPosts.forEach { postResponse ->
                            val post = Post(
                                id = postResponse.id,
                                title = postResponse.title,
                                body = postResponse.body
                            )
                            posts.value?.add(post)
                            getAuthor(post.id, postResponse.userId)
                            getComments(post.id)
                        }
                    } else {
                        print("GET POSTS ERROR: ${response.code()}")
                    }
                } catch (e: Exception) {
                    print("GET POSTS EXCEPTION: ${e.message}")
                } catch (e: Throwable) {
                    print("GET POSTS EXCEPTION: ${e.message}")
                }
            }
        }
    }

    private suspend fun getAuthor(postId: Int, authorId: Int) {
        coroutineScope {
            val deferred = async(Dispatchers.IO) {
                apiClient.getAuthor(authorId)
            }
            val response = deferred.await()

            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val authorResponse = response.body()!!
                        posts.value!!.find { post -> post.id == postId }
                            ?.apply { author = authorResponse }
                        posts.value = posts.value // This updates the live data value
                    } else {
                        print("GET AUTHOR ERROR: ${response.code()}")
                    }
                } catch (e: Exception) {
                    print("GET AUTHOR EXCEPTION: ${e.message}")
                } catch (e: Throwable) {
                    print("GET AUTHOR EXCEPTION: ${e.message}")
                }
            }
        }
    }

    private suspend fun getComments(postId: Int) {
        coroutineScope {
            val deferred = async(Dispatchers.IO) {
                apiClient.getPostComments(postId)
            }
            val response = deferred.await()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val commentsResponse = response.body()!!
                        posts.value!!.find { post -> post.id == postId }
                            ?.apply { comments = commentsResponse }
                        posts.value = posts.value // This updates the live data value
                    } else {
                        print("GET AUTHOR ERROR: ${response.code()}")
                    }
                } catch (e: Exception) {
                    print("GET AUTHOR EXCEPTION: ${e.message}")
                } catch (e: Throwable) {
                    print("GET AUTHOR EXCEPTION: ${e.message}")
                }
            }
        }
    }

}