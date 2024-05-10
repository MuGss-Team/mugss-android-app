package com.mugss.core.data.di

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    companion object {

        @Provides
        @Singleton
        @UserPrefs
        fun provideUserPrefs(
            @ApplicationContext context: Context,
        ): SharedPreferences = context.getSharedPreferences(
            USER_PREFS,
            Context.MODE_PRIVATE,
        )

        @Provides
        internal fun provideMasterKey(@ApplicationContext context: Context): MasterKey {
            return MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()
        }

        @Singleton
        @Provides
        @TokenPrefs
        internal fun provideTokenSharedPrefs(
            @ApplicationContext context: Context,
            masterKey: MasterKey,
        ): SharedPreferences {
            try {
                return createEncryptedSharedPreferences(context, masterKey)
            } catch (e: Exception) {
                context.clearMasterKey()
            }
            return createEncryptedSharedPreferences(context, masterKey)
        }

        private fun Context.clearMasterKey() {
            getSharedPreferences(
                TOKEN_STORE,
                Context.MODE_PRIVATE,
            ).edit {
                clear()
            }
        }

        private fun createEncryptedSharedPreferences(
            @ApplicationContext
            context: Context,
            masterKey: MasterKey,
        ): SharedPreferences {
            return EncryptedSharedPreferences.create(
                context,
                TOKEN_STORE,
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
            )
        }

        private const val TOKEN_STORE = "token_store"
        private const val USER_PREFS = "user_prefs"
    }
}