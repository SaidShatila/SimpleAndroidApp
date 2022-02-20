package com.example.simpleandroidapp.network

import com.example.simpleandroidapp.base.BaseApiResponse
import com.example.simpleandroidapp.data.remote.RemoteTodosSource
import com.example.simpleandroidapp.data.remote.response.Todos
import com.example.simpleandroidapp.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(private val remoteTodosSource: RemoteTodosSource) :
    BaseApiResponse() {


    suspend fun fetchTodos(): Flow<NetworkResult<List<Todos>>> {
        return flow {
            emit(safeApiCall {
                remoteTodosSource.fetchTodos()
            })
        }.flowOn(Dispatchers.IO)
    }
}