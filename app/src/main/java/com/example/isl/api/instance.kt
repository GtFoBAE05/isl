package com.example.isl.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object instance {

    val BASE_URL = "http://192.168.43.46:8100"

    val instances:SignApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SignApi::class.java)
    }

}
