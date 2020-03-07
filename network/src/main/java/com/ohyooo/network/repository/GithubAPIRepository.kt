package com.ohyooo.network.repository

import com.ohyooo.network.api.GitHubAPIInterface
import com.ohyooo.network.factory.ErrorConverterFactory
import com.ohyooo.network.model.RateLimitResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubAPIRepository : BaseRepository() {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(ErrorConverterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    suspend fun getRateLimit(): RateLimitResponse = withContext(Dispatchers.IO) {
        return@withContext getResponse {
            retrofit.create(GitHubAPIInterface::class.java).getRateLimit()
        }
    }
}