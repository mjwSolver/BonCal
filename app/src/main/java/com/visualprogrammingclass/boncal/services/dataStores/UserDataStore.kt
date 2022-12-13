package com.visualprogrammingclass.boncal.services.dataStores

import android.content.Context
import android.util.Log
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserDataStore(context: Context) {
    private val dataStore: DataStore<Preferences> = context.createDataStore(name = "userStorage")

    val user: KeyValueDatastore<String> = KeyValueDatastore(
        keyName = "user",
        dataStore = dataStore
    )

    val token: KeyValueDatastore<String> = KeyValueDatastore(
        keyName = "token",
        dataStore = dataStore
    )
}