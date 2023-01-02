package com.visualprogrammingclass.boncal.viewModels

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.emptyPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.visualprogrammingclass.boncal.repositories.DataStoreRepository
import com.visualprogrammingclass.boncal.repositories.EndPointRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val endRepository: EndPointRepository,
    private val dataRepository: DataStoreRepository
    ): ViewModel() {

    private val _token: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    private val _widget: MutableLiveData<String> by lazy { MutableLiveData<String>("") }
    val widget: LiveData<String> get()=_widget

    // Async and Await moment?
    fun getLatestWidgetData() = viewModelScope.launch {

        getUserToken().invokeOnCompletion {
            if(it === null){

                viewModelScope.launch {
                    endRepository.getWidgetData(_token.value.toString()).let{ widgetResponse ->

                        if(widgetResponse.isSuccessful) {

                            widgetResponse.body()?.let { widgetContent ->
                                _widget.postValue(widgetContent.data.image)
                            }

                        } else {
                            Log.d("Widget", "Data Retrieval Failed")
                        }
                    }
                }

            }
        }

    }

//    fun getLatestWidgetData() = viewModelScope.launch {
//
//        getUserToken()
//
//        endRepository.getWidgetData(_token.value.toString()).let{ widgetResponse ->
//
//            if(widgetResponse.isSuccessful) {
//
//                widgetResponse.body()?.let { widgetContent ->
//                    _widget.postValue(widgetContent.data.image)
//                }
//
//            } else {
//                Log.d("Widget", "Data Retrieval Failed")
//            }
//
//        }
//    }

    fun getUserToken() = viewModelScope.launch(Dispatchers.IO) {
        dataRepository.readState(DataStoreRepository.PreferencesKey.userTokenKey).collect{ token->

            if(token == emptyPreferences()) { return@collect }
            if(token !is String) { return@collect }

            _token.postValue(token.toString())
        }
    }


}