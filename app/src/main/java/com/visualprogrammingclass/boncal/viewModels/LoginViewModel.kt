package com.visualprogrammingclass.boncal.viewModels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visualprogrammingclass.boncal.models.authentication.LoginDetail
import com.visualprogrammingclass.boncal.repositories.DataStoreRepository
import com.visualprogrammingclass.boncal.repositories.EndPointRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val endRepository:EndPointRepository,
    private val dataRepository:DataStoreRepository
    ): ViewModel() {

    // Observables
    // =================
//    private val _token: MutableLiveData<String> by lazy { MutableLiveData<String>() }
//    val token: LiveData<String> get() = _token

    private val _name: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val name: LiveData<String> get() = _name

    private val _success: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val success: LiveData<Boolean> get() = _success

    // Login Function
    // =================
    fun loginThisUser(theContext: Context, loginDetail: LoginDetail) = viewModelScope.launch {

        endRepository.loginUser(loginDetail).let { loginResponse ->
            Log.d("login_response", loginResponse.body()?.message.toString())
            if (loginResponse.isSuccessful) {

                loginResponse.body()?.let {
                    Toast.makeText(theContext, "Login ${it.message}", Toast.LENGTH_SHORT).show()
                    saveUserToken(it.data.token)
                    saveUserDataWithToken(it.data.token)
                    _success.postValue(true)
                }
                // swap remember me state with just the token
                saveOnRememberMeState(loginDetail.remember)
                // save dulu user from UserData

            } else {

                loginResponse.body()?.let {
                    Log.e("login, loginVM", "Login ${it.message}")
                    Toast.makeText(theContext, "Login ${it.message}", Toast.LENGTH_SHORT).show()
                }
                _success.postValue(false)
            }

        }
    }

    // Remember Me Function
    // =================
    fun saveOnRememberMeState(rememberMe: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.savedBooleanState(
                DataStoreRepository.PreferencesKey.rememberMeKey,
                rememberMe
            )
        }

    // Save User Token Function
    // =================
    fun saveUserToken(token:String) = viewModelScope.launch(Dispatchers.IO) {
        dataRepository.saveState(
            DataStoreRepository.PreferencesKey.userTokenKey,
            token
        )
    }

    // Get User Data <- API
    // saveUserDataAsString() -> DataStore
    // =================
    fun saveUserDataWithToken(token:String) = viewModelScope.launch {
        endRepository.getUserData(token).let { userResponse ->
            // save it to json
            if(userResponse.isSuccessful){
                userResponse.body()?.let { user ->
                    saveUserDataAsString(user.data.toJson())
                }
            } else {
                Log.e("Saving UserData", "Saving Failed")
            }
        }
    }
    fun saveUserDataAsString(userDataAsString:String) = viewModelScope.launch(Dispatchers.IO) {
        dataRepository.saveState(
            DataStoreRepository.PreferencesKey.userDataKey,
            userDataAsString
        )
    }







}