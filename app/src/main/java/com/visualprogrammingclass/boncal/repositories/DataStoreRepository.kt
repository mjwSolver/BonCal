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

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "boncal_preferences")

object PreferencesKey {
    val generalKey = stringPreferencesKey("settings")

    val onBoardingKey = booleanPreferencesKey("on_boarding_completed")
    val rememberMeKey = booleanPreferencesKey("remember_me")

    val userTokenKey = stringPreferencesKey("user_token")
    val userDataKey = stringPreferencesKey("user_data_as_string") // this is json actually
}

class DataStoreRepository(context: Context) {
    private val dataStore = context.dataStore


    // =================================
    // baseModel
    // =================================

    suspend fun saveToDataStore(value: String) {
        dataStore.edit { settings ->
            settings[PreferencesKey.generalKey] = value
        }
    }

    val readFromDataStore: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.d("DataStore", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preference ->
            val myName = preference[PreferencesKey.generalKey] ?: "none"
            myName
        }

    // =================================
    // Modular
    // =================================

    suspend fun <T> saveState(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    suspend fun <T> readState(key: Preferences.Key<T>): Flow<Any> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val readings = preferences[key] ?: emptyPreferences()
                readings
            }
    }

    // =================================
    // boolean - Experimental
    // =================================

    suspend fun savedBooleanState(key: Preferences.Key<Boolean>, bool: Boolean){
        dataStore.edit { preferences ->
            preferences[key] = bool
        }
    }

    fun readBooleanState(key: Preferences.Key<Boolean>): Flow<Boolean>{
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val booleanState = preferences[key] ?: false
                booleanState
            }
    }

    // =================================
    // onBoarding
    // =================================
    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = completed
            Log.d("onBoarding", "dataStore saves $completed")
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