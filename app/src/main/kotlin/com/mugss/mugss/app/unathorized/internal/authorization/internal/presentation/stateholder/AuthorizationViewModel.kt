package com.mugss.mugss.app.unathorized.internal.authorization.internal.presentation.stateholder

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.mugss.core.data.token.UserPrefs
import com.mugss.mugss.app.authorized.api.navigation.AuthorizedGraph
import com.mugss.mugss.app.unathorized.internal.authorization.internal.presentation.contract.AuthorizationState
import com.mugss.mugss.app.unathorized.internal.data.UserRegistrationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AuthorizationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userRegistrationRepository: UserRegistrationRepository,
    private val userPrefs: UserPrefs,
) : ViewModel() {

    @OptIn(SavedStateHandleSaveableApi::class)
    var state by savedStateHandle.saveable {
        mutableStateOf(AuthorizationState())
    }

    private val _navigationFlow: MutableSharedFlow<Any> = MutableSharedFlow()

    val navigationFlow: SharedFlow<Any>
        get() = _navigationFlow.asSharedFlow()

    fun onLoginChange(value: String) {
        state = state.copy(
            login = value
        )
    }

    fun onPasswordChange(value: String) {
        state = state.copy(
            password = value
        )
    }

    fun onAuthorizeButtonClick() = viewModelScope.launch {
        userRegistrationRepository.auth(
            state.login,
            state.password
        ).onSuccess {
            userPrefs.rememberMe = state.rememberMe
            _navigationFlow.emit(AuthorizedGraph)
        }
    }

    fun onCheckboxClicked(
        rememberMe: Boolean,
    ) {
        state = state.copy(
            rememberMe = rememberMe
        )
    }
}