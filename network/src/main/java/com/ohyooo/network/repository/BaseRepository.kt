package com.ohyooo.network.repository

import com.ohyooo.network.factory.ErrorConverterFactory
import com.ohyooo.network.model.BaseResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseRepository {

    private val httpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .callTimeout(1, TimeUnit.MINUTES)
            .build()

    val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(ErrorConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

    inline fun <reified T : BaseResponse> getResponse(method: () -> T): T {
        var resp = T::class.java.newInstance()
        try {
            resp = method.invoke()
        } catch (e: HttpException) {
            e.printStackTrace()
            resp.errorCode = e.code()
            resp.errorMsg = e.message()
        } catch (e: Exception) {
            resp.errorCode = 1
            resp.errorMsg = e.message
        }
        return resp
    }

}