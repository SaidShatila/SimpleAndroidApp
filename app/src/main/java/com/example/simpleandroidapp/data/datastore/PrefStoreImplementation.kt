package com.example.simpleandroidapp.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private const val STORE_NAME = "simple_app_data_store"

@Singleton
class PrefStoreImplementation @Inject constructor(
    @ApplicationContext context: Context

) : PrefStore {
    private val Context._dataStore: DataStore<Preferences> by preferencesDataStore(STORE_NAME)

    private val dataStore: DataStore<Preferences> = context._dataStore
    override fun getRememberMeState(): Flow<Boolean> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { it[PreferenceKeys.REMEMBER_ME_STATE] ?: false }

    override suspend fun saveRememberMeState(rememberMe: Boolean) {
        dataStore.edit {
            it[PreferenceKeys.REMEMBER_ME_STATE] = rememberMe
        }
    }

    private object PreferenceKeys {
        val REMEMBER_ME_STATE = booleanPreferencesKey("remember_me")
    }
}