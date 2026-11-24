package com.alanturing.lookeo_movil.features.auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alanturing.lookeo_movil.features.auth.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel()
{
    private val _state: MutableStateFlow<LoginUiState> =
        MutableStateFlow(value = LoginUiState.Loading)

    private val _date: MutableStateFlow<LoginUiDate> =
        MutableStateFlow(value = LoginUiDate())

    val state: StateFlow<LoginUiState>
        get() = _state.asStateFlow()

    val date: StateFlow<LoginUiDate>
        get() = _date.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = LoginUiState.Success
        }
    }

    fun login() {
        viewModelScope.launch {
            _state.value = LoginUiState.Loading

            val result = userRepository.login(
                email = date.value.userName,
                password = date.value.password
            )

            result.onSuccess {
                _state.value = LoginUiState.Success
            }.onFailure { error ->
                _state.value = LoginUiState.Error(
                    error.message ?: "Error al iniciar sesión"
                )
            }
        }
    }

    fun updateName(newName:String){
        val oldState = date.value
        _date.value = oldState.copy(
            userName = newName
        )
    }

    fun updatePassword(newPassword:String){
        val oldState = date.value
        _date.value = oldState.copy(
            password = newPassword
        )
    }
}

sealed class LoginUiState {
    object Loading: LoginUiState()
    object Success : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}

data class LoginUiDate(
    val userName:String = "",
    val password:String = "",
    val exite:Boolean = false,
)