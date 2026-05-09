package com.example.praktam_2477051012.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL =
        "https://gist.githubusercontent.com/fitriarhma02/2491020dd5ba207f145b2229272788bc/raw/fb151e47c283b6b67c74934a699bc2b82e95c906/"

    val instance: ApiService by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(ApiService::class.java)
    }
}