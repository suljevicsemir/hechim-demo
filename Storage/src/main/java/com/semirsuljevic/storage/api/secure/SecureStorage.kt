package com.semirsuljevic.storage.api.secure
import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
interface SecureStorage {

    fun deleteAll()

    fun storeIntValue(key: String, value: Int)
}
