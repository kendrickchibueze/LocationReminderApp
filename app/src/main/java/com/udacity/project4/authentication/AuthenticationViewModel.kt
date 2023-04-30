package com.udacity.project4.authentication

import androidx.lifecycle.map
import androidx.lifecycle.ViewModel

class AuthenticationViewModel: ViewModel() {

    enum class AuthenticationState {
        AUTHENTICATED,
        UNAUTHENTICATED,
        INVALID_AUTHENTICATION
    }

    val authenticationState = FirebaseUserLiveData().map { user ->
        when (user) {
            null -> AuthenticationState.UNAUTHENTICATED
            else -> AuthenticationState.AUTHENTICATED
        }
    }

}