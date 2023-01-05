package com.visualprogrammingclass.boncal.viewModels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visualprogrammingclass.boncal.models.authentication.RegisterDetail
import com.visualprogrammingclass.boncal.repositories.DataStoreRepository
import com.visualprogrammingclass.boncal.repositories.EndPointRepository
import com.visualprogrammingclass.boncal.repositories.PreferencesKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val endRepository: EndPointRepository,
    private val dataRepository: DataStoreRepository
) :ViewModel() {

    private val _success: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>(false) }
    val success: LiveData<Boolean> get()=_success

    // =====================================================
    // =====================================================

    fun registerThisUser(context: Context, registerdetail: RegisterDetail) = viewModelScope.launch {
        endRepository.registerUser(registerdetail).let { registerResponse ->
            if(registerResponse.isSuccessful){

                registerResponse.body()?.let {
                    Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show()
                    saveUserToken(it.data.token)
                    saveUserDataWithToken(it.data.token)
                    _success.value = true
                    Log.d("regist_success", "${ _success.value }")
                }

            } else {
                registerResponse.body()?.let {
                    Log.e("regist_fail", "Registration ${it.message}")
                    Toast.makeText(context, "Registration ${it.message}", Toast.LENGTH_SHORT).show()
                }
                _success.postValue(false)
            }

        }
    }.invokeOnCompletion { Log.d("registerThisUser", "Completed") }

    // Save User Token Function
    // =================
    fun saveUserToken(token:String) = viewModelScope.launch(Dispatchers.IO) {
        dataRepository.saveState(
            PreferencesKey.userTokenKey,
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
            PreferencesKey.userDataKey,
            userDataAsString
        )
    }


}