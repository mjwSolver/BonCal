package com.visualprogrammingclass.boncal.viewModels

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visualprogrammingclass.boncal.models.LoginDetail
import com.visualprogrammingclass.boncal.models.LoginResponse
import com.visualprogrammingclass.boncal.models.User
import com.visualprogrammingclass.boncal.repositories.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: RegisterRepository): ViewModel()  {

    private val _token: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val token: LiveData<String> get()=_token

    private val _name: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val name: LiveData<String> get()=_name

//    private val _email: MutableLiveData<String> by lazy { MutableLiveData<String>() }
//    val email: LiveData<String> get()=_email

    // =====================================================
    // =====================================================

    fun loginThisUser(theContext: Context, loginDetail: LoginDetail) = viewModelScope.launch {
        repository.loginUser(loginDetail).let { response ->
            if(response.isSuccessful) {

                response.body()?.let {
                    Toast.makeText(theContext, it.message, Toast.LENGTH_SHORT).show()
                    _name.postValue(it.data.name)
                    _token.postValue(it.data.token)
                }

            }
        }
    }


}