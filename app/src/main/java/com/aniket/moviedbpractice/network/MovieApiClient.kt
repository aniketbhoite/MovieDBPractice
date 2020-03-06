package com.aniket.moviedbpractice.network

import com.aniket.moviedbpractice.util.BASE_URL
import com.aniket.moviedbpractice.util.MOVIEDB_API_KEY
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object MovieApiClient {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private val interceptor: Interceptor by lazy {
        Interceptor { chain ->
            val ogRequest: Request = chain.request()

            val url = ogRequest.url.newBuilder()
                .addQueryParameter("api_key", MOVIEDB_API_KEY)
                .build()

            val requestBuilder = ogRequest.newBuilder().url(url)

            return@Interceptor chain.proceed(requestBuilder.build())
        }


    }

    private val loggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
    }


    val apiServices: ApiServices by lazy {
        retrofit.create(ApiServices::class.java)
    }

    val moshi: Moshi by lazy {
        Moshi.Builder().build()
    }
}