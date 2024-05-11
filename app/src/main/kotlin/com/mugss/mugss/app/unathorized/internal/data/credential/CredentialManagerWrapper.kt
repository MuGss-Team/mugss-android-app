package com.mugss.mugss.app.unathorized.internal.data.credential

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class CredentialManagerWrapper @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getCredentialRequest: GetCredentialRequest,
) {
    private val credentialManager = CredentialManager.create(context)

    suspend fun getGoogleIdToken() = withContext(Dispatchers.IO) {
        runCatching<GoogleIdTokenCredential> {
            when (val credential = credentialManager.getCredential(
                context = context,
                request = getCredentialRequest
            ).credential) {
                is CustomCredential -> {
                    if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                        return@runCatching GoogleIdTokenCredential.createFrom(credential.data)
                    }
                    throw GoogleSignInException()
                }

                else -> throw UnsupportedTypeOfCredential()
            }
        }
    }

    suspend fun signOut() = withContext(Dispatchers.IO) {
        credentialManager.clearCredentialState(ClearCredentialStateRequest())
    }
}