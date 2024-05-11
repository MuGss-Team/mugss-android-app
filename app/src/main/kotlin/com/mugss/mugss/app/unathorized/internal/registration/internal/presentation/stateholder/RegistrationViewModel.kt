package com.mugss.mugss.app.unathorized.internal.registration.internal.presentation.stateholder

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.mugss.mugss.app.unathorized.internal.data.user.UserRegistrationRepository
import com.mugss.mugss.app.unathorized.internal.registration.internal.contract.RegistrationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class RegistrationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userRegistrationRepository: UserRegistrationRepository,
) : ViewModel() {

    @OptIn(SavedStateHandleSaveableApi::class)
    var state by savedStateHandle.saveable {
        mutableStateOf(RegistrationState())
    }
        private set

    fun onLoginChange(value: String) {
        state = state.copy(
            login = value
        )
    }

    fun onEmailChange(value: String) {
        state = state.copy(
            email = value
        )
    }

    fun onPasswordChange(value: String) {
        state = state.copy(
            password = value
        )
    }

    fun onPasswordConfirmationChange(value: String) {
        state = state.copy(
            confirmationPassword = value
        )
    }

    fun onRegisterClick() = viewModelScope.launch {
        userRegistrationRepository.register(
            login = state.login,
            email = state.email,
            password = state.password,
        )
    }
}


