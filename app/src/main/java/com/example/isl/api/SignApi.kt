package com.example.isl.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.Part
import retrofit2.http.Path

interface SignApi {

    @GET("/randomsign")
    fun getRandomSign(): Call<Sign>



    @GET("/word/{cari}")
    fun getSign(@Path("cari") cari:String?=null):Call<Sign>

}