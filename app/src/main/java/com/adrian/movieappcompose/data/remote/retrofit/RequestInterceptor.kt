package com.adrian.movieappcompose.data.remote.retrofit

import com.adrian.movieappcompose.data.KeyProvider
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url =
            request.url.newBuilder().addQueryParameter(KeyProvider.API_PARAM, KeyProvider.API_KEY)
                .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}