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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class SplashViewModel @Inject constructor(repository: DataStoreRepository): ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Screen.Welcome.route)
    val startDestination: State<String> = _startDestination

    val _theOnBoard: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
//    val theOnBoard: LiveData<Boolean> get() = _theOnBoard

    val _theRemembered: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
//    val theRemembered: LiveData<Boolean> get() = _theRemembered

    init {
        // Not working
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