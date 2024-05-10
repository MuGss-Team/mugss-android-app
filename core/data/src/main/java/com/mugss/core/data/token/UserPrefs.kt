package com.mugss.core.data.token

import android.content.SharedPreferences
import com.mugss.core.data.di.UserPrefs
import javax.inject.Inject

class UserPrefs @Inject constructor(
    @UserPrefs sharedPreferences: SharedPreferences,
) {
    var rememberMe by sharedPreferences.boolean("remember_me", false)
}