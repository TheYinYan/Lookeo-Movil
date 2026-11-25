    package com.alanturing.lookeo_movil.features.auth.ui

	import android.util.Patterns
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

	data class RegisterUiState(
		val username: String = "",
		val email: String = "",
		val password: String = "",
		val confirmPassword: String = "",
		val acceptedTerms: Boolean = false,
		val isLoading: Boolean = false,

		@StringRes val errorMessageResId: Int? = null,
		val errorMessage: String? = null,
		val isSuccess: Boolean = false,
	)

    @HiltViewModel
	class RegisterViewModel @Inject constructor(
		private val userRepository: UserRepository,
	) : ViewModel() {
	
		private val _uiState = MutableStateFlow(RegisterUiState())
		val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

		fun onUsernameChanged(value: String) {
			_uiState.update {
				it.copy(username = value, errorMessageResId = null, errorMessage = null)
			}
		}

		fun onEmailChanged(value: String) {
			_uiState.update {
				it.copy(email = value, errorMessageResId = null, errorMessage = null)
			}
		}

		fun onPasswordChanged(value: String) {
			_uiState.update {
				it.copy(password = value, errorMessageResId = null, errorMessage = null)
			}
		}

		fun onConfirmPasswordChanged(value: String) {
			_uiState.update {
				it.copy(confirmPassword = value, errorMessageResId = null, errorMessage = null)
			}
		}

		fun onAcceptedTermsChanged(accepted: Boolean) {
			_uiState.update {
				it.copy(acceptedTerms = accepted, errorMessageResId = null, errorMessage = null)
			}
		}

		fun register() {
			val state = _uiState.value
			val username = state.username.trim()
			val email = state.email.trim()
			val password = state.password

			if (username.isBlank() || email.isBlank() || password.isBlank() || state.confirmPassword.isBlank()) {
				_uiState.update {
					it.copy(errorMessageResId = R.string.register_fill_all, errorMessage = null)
				}
				return
			}

			if (username.length < 3) {
				_uiState.update {
					it.copy(errorMessageResId = R.string.register_error_name, errorMessage = null)
				}
				return
			}

			if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
				_uiState.update {
					it.copy(errorMessageResId = R.string.register_error_email, errorMessage = null)
				}
				return
			}

			if (password.length < 8) {
				_uiState.update {
					it.copy(errorMessageResId = R.string.register_error_password, errorMessage = null)
				}
				return
			}

			if (password != state.confirmPassword) {
				_uiState.update {
					it.copy(errorMessageResId = R.string.register_error_password_mismatch, errorMessage = null)
				}
				return
			}

			if (!state.acceptedTerms) {
				_uiState.update {
					it.copy(errorMessageResId = R.string.register_error_terms, errorMessage = null)
				}
				return
			}

			viewModelScope.launch {
				_uiState.update {
					it.copy(isLoading = true, errorMessageResId = null, errorMessage = null)
				}

				val result = userRepository.register(
					email = email,
					name = username,
					password = password,
				)

				result.fold(
					onSuccess = {
						_uiState.update { it.copy(isLoading = false, isSuccess = true) }
					},
					onFailure = { error ->
						_uiState.update {
							it.copy(
								isLoading = false,
								errorMessageResId = null,
								errorMessage = error.message
									?: "Error al registrar usuario",
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
