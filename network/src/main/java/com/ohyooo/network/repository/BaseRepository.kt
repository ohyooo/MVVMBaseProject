package com.ohyooo.network.repository

import android.util.Log
import com.ohyooo.network.model.BaseResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import java.util.concurrent.TimeUnit

abstract class BaseRepository {

    val httpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .callTimeout(1, TimeUnit.MINUTES)
            .build()

    inline fun <reified T : BaseResponse> getResponse(method: () -> T): T {
        var resp = T::class.java.newInstance()
        try {
            resp = method.invoke()
        } catch (e: HttpException) {
            Log.e("BaseRepository", "getResponse() " + T::class.java.name)
            resp.errorCode = e.code()
            resp.errorMsg = e.message
        } catch (e: Exception) {
            resp.errorCode = 1
            resp.errorMsg = e.message
        }
        return resp
    }

}