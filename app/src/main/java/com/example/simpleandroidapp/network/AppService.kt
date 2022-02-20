package com.example.simpleandroidapp.network


import com.example.simpleandroidapp.data.remote.response.Todos
import retrofit2.Response
import retrofit2.http.GET

interface AppService {
    @GET("todos")
    suspend fun getTodos() : Response<List<Todos>>
}