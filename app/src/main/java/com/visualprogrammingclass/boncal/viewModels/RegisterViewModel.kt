package com.visualprogrammingclass.boncal.viewModels

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.*
import com.visualprogrammingclass.boncal.models.LoginDetail
import com.visualprogrammingclass.boncal.models.RegisterDetail
import com.visualprogrammingclass.boncal.repositories.DataStoreRepository
import com.visualprogrammingclass.boncal.repositories.RegisterRepository
import com.visualprogrammingclass.boncal.services.dataStores.UserDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
            private val repository: RegisterRepository
        )
    :ViewModel() {

//    private val userDataStore = UserDataStore(context)
//    val readFromUserDataStore = userDataStore.user.getValue

//    private val theDataStore = DataStoreRepository(Application())
//    val info = theDataStore.readFromDataStore

    private val _token: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val token: LiveData<String> get() = _token

    private val _tokenName: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val tokenName: LiveData<String> get() = _tokenName

    private val _name: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val name: LiveData<String> get() = _name

//    private val _email: MutableLiveData<String> by lazy { MutableLiveData<String>() }
//    val email: LiveData<String> get()=_email

    // =====================================================
    // =====================================================

    fun loginThisUser(theContext: Context, loginDetail: LoginDetail) = viewModelScope.launch {
        repository.loginUser(loginDetail).let { response ->
            if (response.isSuccessful) {

                response.body()?.let {
                    Toast.makeText(theContext, it.message, Toast.LENGTH_SHORT).show()
                    _tokenName.postValue(it.data.token_name)
                    _token.postValue(it.data.token)
                    _name.postValue(it.data.user.name)
                }

            } else {
                Log.e("login, RegisterVM", "Error Occured")
            }
        }
    }

    fun registerThisUser(theContext: Context, registerdetail: RegisterDetail) = viewModelScope.launch {
        repository.registerUser(registerdetail).let { response ->
            if(response.isSuccessful){

                response.body()?.let {  }

            }
        }
    }



//    fun


}