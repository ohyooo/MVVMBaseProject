package com.ohyooo.network.factory

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import com.ohyooo.network.model.BaseResponse
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class ErrorConverterFactory : Converter.Factory() {

    override fun responseBodyConverter(type: Type?, annotations: Array<Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, *>? {
        val gson = Gson()
        val adapter = gson.getAdapter(TypeToken.get(type))
        return ResponseConverter(gson, adapter)
    }
}

class ResponseConverter<T>(private val gson: Gson, private val adapter: TypeAdapter<T>) : Converter<ResponseBody, T> {
    override fun convert(value: ResponseBody): T? {
        try {
            val originalBody = value.string()
            return if (isJSONValid(originalBody)) {
                adapter.fromJson(originalBody)
            } else {
                val resp = BaseResponse()
                resp.errorCode = -1
                resp.errorMsg = "invalid response"
                adapter.fromJson(gson.toJson(resp))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            value.close()
        }
        return null
    }

    private fun isJSONValid(test: String?): Boolean {
        if (test.isNullOrBlank()) {
            return false
        }
        try {
            JSONObject(test)
        } catch (e: JSONException) {
            try {
                JSONArray(test)
            } catch (e: JSONException) {
                return false
            }
        }
        return true
    }
}