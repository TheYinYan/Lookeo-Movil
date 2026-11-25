package com.alanturing.lookeo_movil.features.profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alanturing.lookeo_movil.features.auth.data.local.datasource.TokenLocalDataSourceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val tokenDataSource: TokenLocalDataSourceImpl
) : ViewModel(){

    fun logout(onSuccess: () -> Unit) {
        viewModelScope.launch {
            tokenDataSource.clearToken()
            onSuccess()
        }
    }
}