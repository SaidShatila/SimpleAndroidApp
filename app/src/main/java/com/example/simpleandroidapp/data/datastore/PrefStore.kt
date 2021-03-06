package com.example.simpleandroidapp.data.datastore

import kotlinx.coroutines.flow.Flow

interface PrefStore {

    fun getRememberMeState(): Flow<Boolean>
    suspend fun saveRememberMeState(rememberMe: Boolean)

    fun getUserLoginDate(): Flow<String>
    suspend fun setUserDateLogin(dateFormat: String)

}