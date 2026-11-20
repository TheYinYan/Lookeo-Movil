package com.alanturing.lookeo_movil.features.auth.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val TOKEN_KEY = stringPreferencesKey("auth_token")
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "lookeo_preferences")
