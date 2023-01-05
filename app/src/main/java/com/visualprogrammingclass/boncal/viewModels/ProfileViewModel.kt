package com.visualprogrammingclass.boncal.viewModels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.datastore.preferences.core.emptyPreferences
import androidx.lifecycle.*
import com.visualprogrammingclass.boncal.helpers.JsonConvertible
import com.visualprogrammingclass.boncal.helpers.JsonConvertible.Companion.fromJson
import com.visualprogrammingclass.boncal.models.authentication.User
import com.visualprogrammingclass.boncal.repositories.DataStoreRepository
import com.visualprogrammingclass.boncal.repositories.PreferencesKey.onBoardingKey
import com.visualprogrammingclass.boncal.repositories.PreferencesKey.userDataKey
import com.visualprogrammingclass.boncal.repositories.PreferencesKey.userTokenKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
//    private val endRepository: EndPointRepository,
    private val dataRepository: DataStoreRepository
) : ViewModel() {

    private val _name: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val name: LiveData<String> get() = _name

    private val _createdAt: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val createdAt: LiveData<String> get() = _createdAt

    private val _email: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val email: LiveData<String> get() = _email

    private val _totalCarbonEmission: MutableLiveData<Double> by lazy { MutableLiveData<Double>() }
    val totalCarbonEmission: LiveData<Double> get() = _totalCarbonEmission

//    init{
//        getUserData(context)
//    }

    fun getUserData(context: Context) = viewModelScope.launch {
        dataRepository.readState(userDataKey).collect { userData ->
            Log.d("ProfileVM", "Collection start")

            if (!userData.equals(emptyPreferences())) {
                val theUser = fromJson(userData.toString(), User::class.java)

                Log.d("ProfileVM", "we're in")
                withContext(Dispatchers.Main) {
//                    _name.postValue(userData.Name)
//                    _email.postValue(userData.Email)
//                    _createdAt.postValue(userData.CreatedAt)
                    _name.value = theUser.Name
                    _email.value = theUser.Email
                    _createdAt.value = theUser.CreatedAt
                    _totalCarbonEmission.value =
                        (Math.round(theUser.total_carbon_emissions * 10.0) / 10.0)

                    Log.d("ProfileVM", "name: ${_name.value}")
                    Log.d("ProfileVM", "email: ${_email.value}")
                    Log.d("ProfileVM", "createdAt: ${_createdAt.value}")
                    Log.d("ProfileVM", "totalCarbon: ${_totalCarbonEmission.value}")
                }
            } else {
                Toast.makeText(context, "failed to Load data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun logOut() {
        deleteStoredUserData()
        deleteStoredUserToken()
        deleteOnboardingStatus()
    }

    fun repeatOnboarding() {
        deleteOnboardingStatus()
        deleteStoredUserToken()
    }

    fun deleteStoredUserData() = viewModelScope.launch(Dispatchers.IO + SupervisorJob()) {
        dataRepository.saveState(userDataKey, "")
    }

    fun deleteStoredUserToken() = viewModelScope.launch(Dispatchers.IO + SupervisorJob()) {
        dataRepository.saveState(userTokenKey, "")
    }

    fun deleteOnboardingStatus() = viewModelScope.launch(Dispatchers.IO + SupervisorJob()) {
        dataRepository.saveState(onBoardingKey, false)
    }

}