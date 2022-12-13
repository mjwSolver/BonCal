package com.visualprogrammingclass.boncal.repositories

import android.content.Context
import android.util.Log
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreRepository(context: Context) {
    private val dataStore: DataStore<Preferences> = context.createDataStore("Settings")

    private object PreferencesKey {
        val name = preferencesKey<String>("settings")
    }
    suspend fun saveToDataStore(value: String){
        dataStore.edit{ settings ->
            settings[PreferencesKey.name] = value
        }
    }

    val readFromDataStore: Flow<String> = dataStore.data
        .catch { exception ->
            if(exception is IOException){
                Log.d("DataStore", exception.message.toString())
                emit(emptyPreferences())
            }
            else { throw exception }
        }
        .map { preference ->
            val myName = preference[PreferencesKey.name] ?: "none"
            myName
        }
}