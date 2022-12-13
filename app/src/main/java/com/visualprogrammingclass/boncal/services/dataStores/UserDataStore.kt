package com.visualprogrammingclass.boncal.services.dataStores

import android.content.Context
import android.preference.PreferenceDataStore
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.preferencesOf
import androidx.datastore.preferences.core.stringPreferencesKey
import java.util.prefs.Preferences
import androidx.compose.runtime.getValue
import javax.sql.DataSource

class UserDataStore(private val context: Context) {
    private val datastore: DataSource<Preferences> = context.createDataStore(
       name = "lol"
   )
}