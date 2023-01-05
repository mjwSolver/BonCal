package com.visualprogrammingclass.boncal.viewModels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.datastore.preferences.core.emptyPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visualprogrammingclass.boncal.helpers.statics
import com.visualprogrammingclass.boncal.repositories.DataStoreRepository
import com.visualprogrammingclass.boncal.repositories.EndPointRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val endRepository: EndPointRepository,
    private val dataRepository: DataStoreRepository
    ): ViewModel() {

    private val _token: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    private val _airQualityWidget: MutableLiveData<String> by lazy { MutableLiveData<String>("") }
    val airQualityWidget: LiveData<String> get()=_airQualityWidget

    init{
        getUserToken()
    }


    // Air Quality Widget
    // =============

    fun getLatestAirQualityWidgetData(context: Context) = viewModelScope.launch {

            val fetchUserToken = async {getUserTokenFlow()}
            val theUserToken = fetchUserToken.await().collect().toString()
            Toast.makeText(context, "Fetching User Token", Toast.LENGTH_SHORT).show()

            endRepository.getWidgetData(theUserToken).let{ widgetResponse ->

                if(widgetResponse.isSuccessful) {

                    widgetResponse.body()?.let { widgetContent ->
                        _airQualityWidget.postValue(widgetContent.data.image)
                    }

                } else {
                    Log.d("Widget", "Data Retrieval Failed")
                }
            }


    }

    // Getting User Token
    // ===================

    private suspend fun getUserTokenFlow(): Flow<String> = flow {
        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.readState(DataStoreRepository.PreferencesKey.userTokenKey).cancellable().collect{ token->

                if(token == emptyPreferences()) { return@collect }
                if(token !is String) { return@collect }

                _token.postValue(token.toString())
                statics.token = token.toString()
                emit(token.toString())
            }
        }
    }

    private fun getUserToken() = viewModelScope.launch(Dispatchers.IO) {
        dataRepository.readState(DataStoreRepository.PreferencesKey.userTokenKey).cancellable().collect{ token->

            if(token == emptyPreferences()) { return@collect }
            if(token !is String) { return@collect }

            _token.postValue(token.toString())
        }
    }


}