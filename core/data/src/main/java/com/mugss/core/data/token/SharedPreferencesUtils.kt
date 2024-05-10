package com.mugss.core.data.token

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal fun SharedPreferences.boolean(key: String, defValue: Boolean) =
    property(
        SharedPreferences::getBoolean,
        SharedPreferences.Editor::putBoolean,
        key,
        defValue
    )

internal fun SharedPreferences.string(key: String, defValue: String) =
    property(
        SharedPreferences::getStringNotNull,
        SharedPreferences.Editor::putString,
        key,
        defValue
    )

private fun SharedPreferences.getStringNotNull(key: String, defValue: String) =
    getString(key, defValue) ?: defValue

private fun <T> SharedPreferences.property(
    get: SharedPreferences.(String, T) -> T,
    set: SharedPreferences.Editor.(String, T) -> SharedPreferences.Editor,
    key: String,
    defValue: T
) = object : ReadWriteProperty<Any?, T> {

    override operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return get(key, defValue)
    }

    override operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        edit().set(key, value).apply()
    }
}