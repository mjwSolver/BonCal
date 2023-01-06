package com.visualprogrammingclass.boncal.viewModels

import android.util.Log
import androidx.datastore.preferences.core.emptyPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.visualprogrammingclass.boncal.helpers.statics
import com.visualprogrammingclass.boncal.models.CarbonEmissionDetail
import com.visualprogrammingclass.boncal.repositories.DataStoreRepository
import com.visualprogrammingclass.boncal.repositories.EndPointRepository
import com.visualprogrammingclass.boncal.repositories.PreferencesKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CalculateViewModel  @Inject constructor(
    private val endRepository: EndPointRepository,
    private val dataRepository: DataStoreRepository
): ViewModel() {

    private val _token: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val token: MutableLiveData<String> get() = _token

    init {
        getUserToken()
    }

    fun sendingCarbonEmissionData(carbonEmissionDetail: CarbonEmissionDetail) = viewModelScope.launch {
        endRepository.sendCarbonEmissionData(_token.value.toString(), carbonEmissionDetail).let {
            if(it.isSuccessful){
                Log.d("CalculateVM", "Sent with Token: ${_token.value.toString()}")
                Log.d("CalculateVM", "Successfully sent Carbon Emission Data")
            } else {
                Log.e("CalculateVM", "Failed to send Carbon Emission Data")
            }
        }
    }

    fun getUserToken() = viewModelScope.launch(Dispatchers.IO) {
        dataRepository.readState(PreferencesKey.userTokenKey).cancellable().collect{ token->

            if(token == emptyPreferences()) { return@collect }
            if(token !is String) { return@collect }
            if(token.isNotEmpty()){

                // this works
                withContext(Dispatchers.Main){
                    _token.value = token.toString()
                    Log.d("CalculateVM", "_token.value is ${_token.value.toString()}")
                }

            }
        }
    }


//    fun updateStoredUserData(newUserData: String) = viewModelScope.launch {
//        dataRepository.saveState(PreferencesKey.userDataKey, newUserData)
//    }

}