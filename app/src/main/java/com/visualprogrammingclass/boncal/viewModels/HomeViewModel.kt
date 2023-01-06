package com.visualprogrammingclass.boncal.viewModels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.datastore.preferences.core.emptyPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.visualprogrammingclass.boncal.helpers.JsonConvertible.Companion.fromJson
import com.visualprogrammingclass.boncal.helpers.statics
import com.visualprogrammingclass.boncal.models.article.ArrayListOfArticleResponse
import com.visualprogrammingclass.boncal.models.article.ArticleResponseItem
import com.visualprogrammingclass.boncal.models.authentication.User
import com.visualprogrammingclass.boncal.repositories.DataStoreRepository
import com.visualprogrammingclass.boncal.repositories.EndPointRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import com.visualprogrammingclass.boncal.models.widgets.WidgetResponse
import com.visualprogrammingclass.boncal.repositories.PreferencesKey

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val endRepository: EndPointRepository,
    private val dataRepository: DataStoreRepository
) : ViewModel() {

    private val _token: MutableLiveData<String> by lazy { MutableLiveData<String>() }
//    private val _airQualityWidget: MutableLiveData<String> by lazy { MutableLiveData<String>("") }
//    val airQualityWidget: LiveData<String> get()=_airQualityWidget

    private val _allWidget: MutableLiveData<List<String>> by lazy { MutableLiveData<List<String>>() }
    val allWidget: LiveData<List<String>> get() = _allWidget

    private val _widgetUrl: MutableLiveData<List<String>> by lazy { MutableLiveData<List<String>>() }
    val widgetUrl: LiveData<List<String>> get() = _widgetUrl

    private val _articles: MutableLiveData<ArrayList<ArticleResponseItem>?> by lazy { MutableLiveData<ArrayList<ArticleResponseItem>?>() }
    val articles: LiveData<ArrayList<ArticleResponseItem>?> get() = _articles

    private val _userEmission: MutableLiveData<Double> by lazy { MutableLiveData<Double>() }
    val userEmission: LiveData<Double> get() = _userEmission

    init {
        getUserToken().invokeOnCompletion {
            Log.d("HomeVM", "_token when complete is now ${_token.value.toString()}")
        }
        Log.d("HomeVM", "_token fresh is now ${_token.value.toString()}")

        getLatestWidgetData()
        getArticles()
    }

    // Articles
    // =============
    fun getArticles() = viewModelScope.launch {
        endRepository.getArticleData().let { arraylistofArticlesResponse ->
            if (arraylistofArticlesResponse.isSuccessful) {

                arraylistofArticlesResponse.body()?.let { arrayListOfArticles ->

                    withContext(Dispatchers.Main) {
                        _articles.value = arrayListOfArticles.data
                        Log.d("HomeVM", "Successfully acquired Articles")
                    }
                }

            } else {
                Log.e("HomeVM", "Failed to get Articles")
            }

        }
    }


    // Air Quality Widget
    // =============

//    fun getLatestAirQualityWidgetData() = viewModelScope.launch {
//
//        endRepository.getWidgetData(_token.value.toString()).let{ widgetResponse ->
//            Log.d("HomeVM", "Getting Widget data with token: ${_token.value.toString()}")
//            if(widgetResponse.isSuccessful) {
//                widgetResponse.body()?.let { widgetContent ->
//
//                    withContext(Dispatchers.Main){
//                        _airQualityWidget.postValue(widgetContent.data[0].image)
//                        Log.d("Widget", widgetContent.data[0].image)
//                    }
//                }
//
//            } else {
//                Log.d("Widget", "Data Retrieval Failed")
//
//            }
//        }
//    }

    fun getLatestWidgetData() = viewModelScope.launch {

        endRepository.getWidgetData(_token.value.toString()).let { widgetResponse ->
            Log.d("HomeVM", "Getting All Widget data with token: ${_token.value.toString()}")
            if (widgetResponse.isSuccessful) {
                widgetResponse.body()?.let { widgetContent ->

                    withContext(Dispatchers.Main) {
                        _allWidget.postValue(widgetContent.data.map { it.image })
                        _widgetUrl.postValue(widgetContent.data.map { it.data })
                        Log.d("Widget", widgetContent.data.map { it.image }.toString())
                    }
                }

            } else {
                Log.d("Widget", "Data Retrieval Failed")

            }
        }
    }


    // Getting User Token
    // ===================

    fun getUserToken() = viewModelScope.launch(Dispatchers.IO) {
        dataRepository.readState(PreferencesKey.userTokenKey).cancellable().collect { token ->

            if (token == emptyPreferences()) {
                return@collect
            }
            if (token !is String) {
                return@collect
            }
            if (token.isNotEmpty()) {

                // this works
                withContext(Dispatchers.Main) {
                    _token.value = token.toString()
                    Log.d("HomeVM", "_token.value is ${_token.value.toString()}")
                }

                // this doesn't work?
//                _token.postValue(token.toString())
//                Log.d("HomeVM", "_token.value is ${_token.value.toString()}")

            }

        }
    }


    // Get User Data <- API
    // saveUserDataAsString() -> DataStore
    // =================
    fun saveUserDataWithToken(token: String = _token.value.toString()) = viewModelScope.launch {
        endRepository.getUserData(token).let { userResponse ->
            // save it to json
            if (userResponse.isSuccessful) {
                userResponse.body()?.let { user ->
                    saveUserDataAsString(user.data.toJson())
                }
            } else {
                Log.e("Saving UserData", "Saving Failed")
            }
        }
    }

    fun saveUserDataAsString(userDataAsString: String) = viewModelScope.launch(Dispatchers.IO) {
        dataRepository.saveState(
            PreferencesKey.userDataKey,
            userDataAsString
        )
    }

    fun readUserDataAsClass() = viewModelScope.launch(Dispatchers.IO) {
        dataRepository.readState(PreferencesKey.userDataKey).cancellable()
            .collect { userDataAsString ->
                if (userDataAsString == emptyPreferences()) {
                    return@collect
                }
                if (userDataAsString !is String) {
                    return@collect
                }
                if (userDataAsString.isNotEmpty()) {
                    val theUser = fromJson(userDataAsString, User::class.java)

                    withContext(Dispatchers.Main) {
                        _userEmission.value = theUser.total_carbon_emissions
                    }
                }
            }
    }


}