package com.alanturing.lookeo_movil.features.auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alanturing.lookeo_movil.features.auth.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _state: MutableStateFlow<AuthUiState> =
        MutableStateFlow(AuthUiState.Loading)

    val state: StateFlow<AuthUiState> =
        _state.asStateFlow()

    fun checkSession() {
        viewModelScope.launch {
            val token = userRepository.getToken()

            if (token == null) {
                _state.value = AuthUiState.Unauthenticated
            } else {
                val user = userRepository.observeUser().first()

                if (user != null) {
                    _state.value = AuthUiState.Authenticated
                } else {
                    _state.value = AuthUiState.Unauthenticated
                }
            }
        }
    }
}

sealed class AuthUiState {
    object Loading : AuthUiState()
    object Authenticated : AuthUiState()
    object Unauthenticated : AuthUiState()
}