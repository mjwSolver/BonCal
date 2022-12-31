package com.visualprogrammingclass.boncal.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.emptyPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visualprogrammingclass.boncal.repositories.DataStoreRepository
import com.visualprogrammingclass.boncal.services.navigations.Screen
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(repository: DataStoreRepository): ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Screen.Welcome.route)
    val startDestination: State<String> = _startDestination

    init {

        val onBoardingScope = viewModelScope.async {
            return@async repository.readOnBoardingState().collect()
        }
        onBoardingScope.invokeOnCompletion {
            Log.d("onBoardingScope", "$onBoardingScope")
        }

        viewModelScope.launch {

            var onBoard = false;
            repository.readOnBoardingState().collect { completed ->
                if(!completed.equals(emptyPreferences())){
                    onBoard = completed
                    Log.d("onBoard", "splashviewModel receives ${onBoard}")
                } else {
                    Log.d("onBoard", "Empty Reference")
                }
            }
            Log.d("onBoard", "onBoard is ${onBoard}")


            // Check jika udh ada token yang udh di store...
            var remembered = false;
            repository.readBooleanState(DataStoreRepository.PreferencesKey.rememberMeKey).collect { remember ->
                if(!remember.equals(emptyPreferences())) {
                    remembered = remember
                    Log.d("rememberMe", remembered.toString())
                } else {
                    remembered = false
                    Log.d("rememberMe", "Empty Reference")
                }
            }

//            if (onBoard && remembered) {
//                _startDestination.value = Screen.Home.route
//            }

            Log.d("onBoard", "before traveling, reads $onBoard")
            if(onBoard) {
                _startDestination.value = Screen.Login.route
            } else {
                _startDestination.value = Screen.Welcome.route
            }

            _isLoading.value = false
        }
    }
}