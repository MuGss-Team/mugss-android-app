package com.mugss.mugss.app.registration.stateholder

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.mugss.mugss.app.registration.contract.RegistrationState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
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
}


