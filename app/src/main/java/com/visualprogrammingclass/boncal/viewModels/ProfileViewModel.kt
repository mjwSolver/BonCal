package com.visualprogrammingclass.boncal.viewModels

import android.service.autofill.UserData
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.emptyPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.visualprogrammingclass.boncal.models.authentication.User
import com.visualprogrammingclass.boncal.repositories.DataStoreRepository
import com.visualprogrammingclass.boncal.repositories.EndPointRepository
import com.visualprogrammingclass.boncal.repositories.PreferencesKey
import com.visualprogrammingclass.boncal.repositories.PreferencesKey.onBoardingKey
import com.visualprogrammingclass.boncal.repositories.PreferencesKey.userDataKey
import com.visualprogrammingclass.boncal.repositories.PreferencesKey.userTokenKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
//    private val endRepository: EndPointRepository,
    private val dataRepository: DataStoreRepository
): ViewModel() {

    private val _name: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val name: LiveData<String> get() = _name

    private val _createdAt: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val createdAt: LiveData<String> get() = _createdAt

    private val _email: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val email: LiveData<String> get() = _email

    init{
        getUserData()
    }

    fun getUserData() = viewModelScope.launch {
        dataRepository.readState(userDataKey).collect{ userData ->
            if(!userData.equals(emptyPreferences()) && (userData is User)){

                withContext(Dispatchers.Main){
                    _name.postValue(userData.Name)
                    _email.postValue(userData.Email)
                    _createdAt.postValue(userData.CreatedAt)
                }

            }
        }
    }

    fun logOut() {
        deleteStoredUserData()
        deleteStoredUserToken()
        repeatOnboarding()
    }

    fun deleteStoredUserData() = viewModelScope.launch(Dispatchers.IO + SupervisorJob()) {
        dataRepository.saveState(userDataKey, "")
    }

    fun deleteStoredUserToken() = viewModelScope.launch(Dispatchers.IO + SupervisorJob()) {
        dataRepository.saveState(userTokenKey, "")
    }

    fun repeatOnboarding() = viewModelScope.launch(Dispatchers.IO + SupervisorJob()) {
        dataRepository.saveState(onBoardingKey, false)
    }

}