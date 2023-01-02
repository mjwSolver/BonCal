package com.visualprogrammingclass.boncal.viewModels

import android.service.autofill.UserData
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.emptyPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.visualprogrammingclass.boncal.models.authentication.User
import com.visualprogrammingclass.boncal.repositories.DataStoreRepository
import com.visualprogrammingclass.boncal.repositories.EndPointRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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

    val userDataKey = DataStoreRepository.PreferencesKey.userDataKey

    fun getUserData() = viewModelScope.launch {
        dataRepository.readState(userDataKey).collect{ it ->
            if(!it.equals(emptyPreferences()) && (it is User)){

                _name.postValue(it.Name)
                _email.postValue(it.Email)
                _createdAt.postValue(it.CreatedAt)

            }
        }
    }

}