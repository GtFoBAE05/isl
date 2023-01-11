package com.example.isl.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.Part
import retrofit2.http.Path

interface SignApi {

    @GET("/random")
    fun getRandomSign(): Call<Sign>

    @GET("/find/{name}")
    fun getSign(@Path("name") name:String?=null):Call<Sign>

}