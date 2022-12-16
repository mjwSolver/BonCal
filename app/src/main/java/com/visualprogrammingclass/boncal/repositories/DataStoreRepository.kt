package com.visualprogrammingclass.boncal.repositories

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.*
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "on_boarding_pref")

class DataStoreRepository(context: Context) {
    private val dataStore = context.dataStore

    private object PreferencesKey {
        val generalKey = stringPreferencesKey("settings")
        val onBoardingKey = booleanPreferencesKey("on_boarding_completed")
    }

    suspend fun saveToDataStore(value: String){
        dataStore.edit{ settings ->
            settings[PreferencesKey.generalKey] = value
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
            val myName = preference[PreferencesKey.generalKey] ?: "none"
            myName
        }

    // =================================
    // onBoarding
    // =================================
    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = completed
        }
    }
    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false
                onBoardingState
            }
    }
}