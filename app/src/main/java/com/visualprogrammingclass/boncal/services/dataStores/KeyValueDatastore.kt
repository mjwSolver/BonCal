package com.visualprogrammingclass.boncal.services.dataStores

import android.util.Log
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.emptyPreferences
import androidx.datastore.preferences.preferencesKey
import com.visualprogrammingclass.boncal.repositories.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class KeyValueDatastore<T : Any>(
    private val keyName: String,
    private val dataStore: DataStore<Preferences>
) {
    private val preferenceKeyName = preferencesKey<Any>(keyName)

    suspend fun saveValue(value: T) {
        dataStore.edit { settings ->
            settings[preferenceKeyName] = value
        }
    }

    val getValue: Flow<Any?> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.d("DataStore", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
//        .map { preference ->
//            val myName = preference[preferenceKeyName]
//            myName
//        }
}