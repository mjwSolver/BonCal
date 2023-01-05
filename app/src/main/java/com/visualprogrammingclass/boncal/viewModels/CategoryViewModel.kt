package com.visualprogrammingclass.boncal.viewModels

import android.util.Log
import androidx.datastore.preferences.core.emptyPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visualprogrammingclass.boncal.models.ArrayListOfEmissionTypes
import com.visualprogrammingclass.boncal.models.EmissionTypesResponse
import com.visualprogrammingclass.boncal.models.SingleAvailableEmissionType
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
class CategoryViewModel @Inject constructor(
    private val endRepository: EndPointRepository,
    private val dataRepository: DataStoreRepository
) : ViewModel() {

    // Jadi nanti kan dalam emission types ada id, we take that ID and associate it to an on click inside
    // this eliminates the need to make anymore.

    private val _token: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val token: LiveData<String> get() = _token

    private val _list: MutableLiveData<List<SingleAvailableEmissionType>> by lazy { MutableLiveData<List<SingleAvailableEmissionType>>() }
    val list: LiveData<List<SingleAvailableEmissionType>> get() = _list

    init{
        getUserToken()
        getEmissionTypes()
    }

    fun getEmissionTypes() = viewModelScope.launch {
        endRepository.getEmissionTypes(_token.value.toString()).let { listOfEmissionResponse ->
            if (listOfEmissionResponse.isSuccessful) {

                listOfEmissionResponse.body()?.let { listOfEmissionTypes ->
                    withContext(Dispatchers.Main) {
                        _list.value = listOfEmissionTypes.data
                    }
                }

            } else {
                Log.e("CategoryVM", "Failed to get Category Information")
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
                    Log.d("HomeVM", "_token.value is ${_token.value.toString()}")
                }

            }

        }
    }


}