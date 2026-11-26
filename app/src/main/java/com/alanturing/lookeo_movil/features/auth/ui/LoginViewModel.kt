package com.alanturing.lookeo_movil.features.auth.ui

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alanturing.lookeo_movil.R
import com.alanturing.lookeo_movil.features.auth.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginUiState(
    val userOrEmail: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    @StringRes val errorMessageResId: Int? = null,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false,
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onUserOrEmailChanged(input: String) {
        _uiState.update {
            it.copy(userOrEmail = input, errorMessageResId = null, errorMessage = null)
        }
    }

    fun onPasswordChanged(input: String) {
        _uiState.update {
            it.copy(password = input, errorMessageResId = null, errorMessage = null)
        }
    }

    fun login() {
        if (_uiState.value.isLoading) return

        val identifier = _uiState.value.userOrEmail.trim()
        val pass = _uiState.value.password

        if (identifier.isBlank() || pass.isBlank()) {
            _uiState.update {
                it.copy(errorMessageResId = R.string.login_fill_both, errorMessage = null)
            }
            return
        }

        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true, errorMessageResId = null, errorMessage = null)
            }

            val result = userRepository.login(
                email = identifier,
                password = pass,
            )

            result.fold(
                onSuccess = {
                    _uiState.update { it.copy(isLoading = false, isSuccess = true) }
                },
                onFailure = {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessageResId = R.string.login_error_credentials,
                            errorMessage = null,
                        )
                    }
                },
            )
        }
    }

    fun resetSuccessState() {
        _uiState.update { it.copy(isSuccess = false) }
    }
}
