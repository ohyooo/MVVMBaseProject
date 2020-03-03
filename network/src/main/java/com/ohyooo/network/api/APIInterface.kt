package com.ohyooo.network.api

import com.ohyooo.network.model.RateLimitResponse
import retrofit2.http.GET

interface APIInterface {

    @GET("rate_limit")
    suspend fun getRateLimit(): RateLimitResponse
}