package com.adrian.movieappcompose.di

import com.adrian.movieappcompose.data.Constants
import com.adrian.movieappcompose.data.remote.retrofit.ApiResultAdapterFactory
import com.adrian.movieappcompose.data.remote.retrofit.RequestInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
//import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
//            .addInterceptor(ChuckInterceptor(context))
            .addInterceptor(RequestInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        val json = kotlinx.serialization.json.Json {
            // By default Kotlin serialization will serialize all of the keys present in JSON object and throw an
            // exception if given key is not present in the Kotlin class. This flag allows to ignore JSON fields
            ignoreUnknownKeys = true
        }

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(json.asConverterFactory(contentType))
            .addCallAdapterFactory(ApiResultAdapterFactory())
            .build()
    }
}
