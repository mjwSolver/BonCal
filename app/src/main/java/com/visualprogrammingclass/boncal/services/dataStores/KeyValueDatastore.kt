package com.visualprogrammingclass.boncal.services.dataStores

class KeyValueDatastore<T : Any>(
    private val keyName: String,
//    private val dataStore: DataStore<Preferences>
) {
//    private val preferenceKeyName = preferencesKey<Any>(keyName)
//
//    suspend fun saveValue(value: T) {
//        dataStore.edit { settings ->
//            settings[preferenceKeyName] = value
//        }
//    }

//    val getValue: Flow<Any?> = dataStore.data
//        .catch { exception ->
//            if (exception is IOException) {
//                Log.d("DataStore", exception.message.toString())
//                emit(emptyPreferences())
//            } else {
//                throw exception
//            }
//        }
//        .map { preference ->
//            val myName = preference[preferenceKeyName]
//            myName
//        }
}