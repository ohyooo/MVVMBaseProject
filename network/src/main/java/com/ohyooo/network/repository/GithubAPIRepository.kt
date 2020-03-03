package com.ohyooo.network.repository

import com.ohyooo.network.api.APIInterface
import com.ohyooo.network.model.RateLimitResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object GithubAPIRepository : BaseRepository() {

    suspend fun getRateLimit(): RateLimitResponse = withContext(Dispatchers.IO) {
        return@withContext getResponse { retrofit.create(APIInterface::class.java).getRateLimit() }
    }
}