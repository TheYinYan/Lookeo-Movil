package com.alanturing.lookeo_movil.features.auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel(){ private val _state: MutableStateFlow<RegisterUiState> =
    MutableStateFlow(value = RegisterUiState.Loading)

    private val _date: MutableStateFlow<RegisterUiDate> =
        MutableStateFlow(value = RegisterUiDate())

    val state: StateFlow<RegisterUiState>
        get() = _state.asStateFlow()

    val date: StateFlow<RegisterUiDate>
        get() = _date.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = RegisterUiState.Success
        }
    }

    fun createName(newName: String) {
        _date.value = _date.value.copy(userName = newName)
    }

    fun createEmail(newEmail: String) {
        _date.value = _date.value.copy(email = newEmail)
    }

    fun createPassword(newPassword: String) {
        _date.value = _date.value.copy(password = newPassword)
    }
}

sealed class RegisterUiState {
    object Loading : RegisterUiState()
    object Success : RegisterUiState()
    data class Error(val message: String) : RegisterUiState()
}

data class RegisterUiDate(
    val userName: String = "",
    val email: String = "",
    val password: String = "",
    val exite: Boolean = false,
)