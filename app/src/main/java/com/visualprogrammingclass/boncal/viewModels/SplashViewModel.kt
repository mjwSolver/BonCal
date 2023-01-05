package com.visualprogrammingclass.boncal.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.datastore.preferences.core.emptyPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visualprogrammingclass.boncal.repositories.DataStoreRepository
import com.visualprogrammingclass.boncal.services.navigations.Screen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class SplashViewModel @Inject constructor(repository: DataStoreRepository): ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Screen.Empty.route)
    val startDestination: State<String> = _startDestination

    init {
        // It's working but it's terrible
        viewModelScope.launch {

            var onBoard = false;
            var theToken = ""
            repository.readOnBoardingState().collect { completed ->

                repository.readState(DataStoreRepository.PreferencesKey.userTokenKey).collect{ token ->
                    if(token != emptyPreferences() && (token is String)) {
                        theToken = token.toString()
                        if(!completed.equals(emptyPreferences())){
                            onBoard = completed
                            Log.d("onBoard", "splashVM receives $onBoard")
                        } else {
        //                    Log.d("onBoard", "Empty Reference")
                        }

                            if(onBoard && theToken.isNotEmpty()){
                                _startDestination.value = Screen.Main.route
                            } else
                                if(onBoard) {
                                _startDestination.value = Screen.Login.route
                            } else {
                                _startDestination.value = Screen.Register.route
                            }
                             _isLoading.value = false

                        Log.d("theToken",  "splashVM receives $theToken")
//                    } else {
//                         theToken = ""
//                        Log.d("theToken", "Empty Reference")
                    }
                }

            }
        }

    }
}