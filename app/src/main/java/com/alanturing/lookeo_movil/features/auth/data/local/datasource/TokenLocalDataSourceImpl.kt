package com.alanturing.lookeo_movil.features.auth.data.local.datasource

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.alanturing.lookeo_movil.features.auth.data.local.datastore.TOKEN_KEY
import com.alanturing.lookeo_movil.features.auth.data.local.datastore.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TokenLocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : TokenLocalDataSource {

    override suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    override suspend fun getToken(): String? {
        return context.dataStore.data
            .map { preferences ->
            preferences[TOKEN_KEY]
            }
            .first()
    }

    override suspend fun clearToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }
}