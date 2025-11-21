package ru.vsls.shared.network.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import javax.inject.Inject
import androidx.core.content.edit

class TokenStorage @Inject constructor(private val context: Context) {

    private companion object {
        const val KEY_TOKEN = "BEARER_TOKEN"
        const val NAME_PREFERENCES = "TOKEN_STORAGE"
    }

    private val masterKey by lazy {
        MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    private val tokenStorage: SharedPreferences by lazy {
        try {
            createEncryptedPrefs()
        } catch (e: Exception) {
            context.deleteSharedPreferences(NAME_PREFERENCES)
            createEncryptedPrefs()
        }
    }

    private fun createEncryptedPrefs(): SharedPreferences {
        return EncryptedSharedPreferences.create(
            context,
            NAME_PREFERENCES,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun saveToken(token: String) {
        tokenStorage.edit { putString(KEY_TOKEN, token) }
    }

    fun getToken(): String? = tokenStorage.getString(KEY_TOKEN, null)

    fun deleteToken() {
        tokenStorage.edit { remove(KEY_TOKEN) }
    }
}