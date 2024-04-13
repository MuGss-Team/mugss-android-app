package com.mugss.core.data.token

import android.content.SharedPreferences
import com.mugss.core.data.di.TokenPrefs
import javax.inject.Inject

class TokenStore @Inject constructor(
    @TokenPrefs sharedPreferences: SharedPreferences,
) {
    var token by sharedPreferences.string("access_token", "")
}