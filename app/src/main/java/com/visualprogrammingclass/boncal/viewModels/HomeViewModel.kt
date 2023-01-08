package com.visualprogrammingclass.boncal.viewModels

import android.util.Log
import androidx.datastore.preferences.core.emptyPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.visualprogrammingclass.boncal.helpers.JsonConvertible.Companion.fromJson
import com.visualprogrammingclass.boncal.models.ReforestationFundsItem
import com.visualprogrammingclass.boncal.models.article.ArticleResponseItem
import com.visualprogrammingclass.boncal.models.authentication.User
import com.visualprogrammingclass.boncal.models.widgets.WidgetResponseItem
import com.visualprogrammingclass.boncal.repositories.DataStoreRepository
import com.visualprogrammingclass.boncal.repositories.EndPointRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import com.visualprogrammingclass.boncal.repositories.PreferencesKey

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val endRepository: EndPointRepository,
    private val dataRepository: DataStoreRepository
) : ViewModel() {

    private val _token: MutableLiveData<String> by lazy { MutableLiveData<String>("nJulRNKdtWGBDXUJvZzftCtucBvXjCtXTAvYDebs") }
//    private val _airQualityWidget: MutableLiveData<String> by lazy { MutableLiveData<String>("") }
//    val airQualityWidget: LiveData<String> get()=_airQualityWidget

//    private val _widgetImage: MutableLiveData<List<String>> by lazy { MutableLiveData<List<String>>() }
//    val widgetImage: LiveData<List<String>> get() = _widgetImage

//    private val _widgetUrl: MutableLiveData<List<String>> by lazy { MutableLiveData<List<String>>() }
//    val widgetUrl: LiveData<List<String>> get() = _widgetUrl

    private val _widgets: MutableLiveData<ArrayList<WidgetResponseItem>> by lazy { MutableLiveData<ArrayList<WidgetResponseItem>>() }
    val widgets: LiveData<ArrayList<WidgetResponseItem>> get() = _widgets

    private val _articles: MutableLiveData<ArrayList<ArticleResponseItem>?> by lazy { MutableLiveData<ArrayList<ArticleResponseItem>?>() }
    val articles: LiveData<ArrayList<ArticleResponseItem>?> get() = _articles

    private val _reforestation: MutableLiveData<ArrayList<ReforestationFundsItem>> by lazy { MutableLiveData<ArrayList<ReforestationFundsItem>>() }
    val reforestation: LiveData<ArrayList<ReforestationFundsItem>?> get() = _reforestation

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

    fun getLatestWidgetData() = viewModelScope.launch {

        endRepository.getWidgetData(_token.value.toString()).let { widgetResponse ->
            Log.d("HomeVM", "Getting All Widget data with token: ${_token.value.toString()}")
            if (widgetResponse.isSuccessful) {
                widgetResponse.body()?.let { widgetContent ->

                    withContext(Dispatchers.Main) {
                        _widgets.postValue(widgetContent.data)
                        Log.d("Widget", widgetContent.data.map { it.image }.toString())
                    }
                }

            } else {
                Log.d("Widget", "Failed to retrieve all Widget Data")

            }
        }
    }

    fun getLatestReforestationProgram() = viewModelScope.launch {
        endRepository.getReforestationPrograms().let { reforestationResponse ->
            if (reforestationResponse.isSuccessful) {
                reforestationResponse.body()?.let { reforestationPrograms ->
                    withContext(Dispatchers.Main) {
                        _reforestation.postValue(reforestationPrograms.data)
                    }
                }
            } else {
                Log.d("HomeVM", "Failed to retrieve reforestation programs")
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