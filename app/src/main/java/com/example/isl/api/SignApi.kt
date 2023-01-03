package com.example.isl.api

import retrofit2.Call
import retrofit2.http.GET

interface SignApi {

    @GET("/randomsign")
    fun getRandomSign(): Call<Sign>

}