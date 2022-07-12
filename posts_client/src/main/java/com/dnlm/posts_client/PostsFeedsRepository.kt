package com.dnlm.posts_client

import Api
import com.dnlm.posts_client.api.ApiConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostsFeedsRepository {
    fun ApiClient(): Api {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiConstants.BASE_URL)
            .build()
            .create(Api::class.java)
    }
}