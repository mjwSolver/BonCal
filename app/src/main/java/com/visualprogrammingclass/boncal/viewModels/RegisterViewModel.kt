package com.visualprogrammingclass.boncal.viewModels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visualprogrammingclass.boncal.models.authentication.LoginDetail
import com.visualprogrammingclass.boncal.models.authentication.RegisterDetail
import com.visualprogrammingclass.boncal.repositories.DataStoreRepository
import com.visualprogrammingclass.boncal.repositories.EndPointRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: EndPointRepository,
    dataStoreRepository: DataStoreRepository
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