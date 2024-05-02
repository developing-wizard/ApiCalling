package com.example.apicalling.api

import com.example.apicalling.datamodels.PostingData
import com.example.apicalling.datamodels.UserData
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/posts")
    fun getPost(): Call<ArrayList<PostingData>>
    @GET("/users")
    fun getUser() : Call<ArrayList<UserData>>
    @GET("/users/{id}")
    fun getUserById(@Path("id") userId: Int) : Call<ArrayList<UserData>>
    @DELETE("/posts/{id}")
    fun deleteById(@Path("id") id : Int):Call<PostingData>
}