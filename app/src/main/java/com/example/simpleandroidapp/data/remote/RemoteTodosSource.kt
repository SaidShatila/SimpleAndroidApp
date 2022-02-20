package com.example.simpleandroidapp.data.remote

import com.example.simpleandroidapp.network.AppService
import javax.inject.Inject

class RemoteTodosSource @Inject constructor(private val appService: AppService) {
    suspend fun fetchTodos() =
        appService.getTodos()

}