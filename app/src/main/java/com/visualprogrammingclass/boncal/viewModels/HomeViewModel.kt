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
import com.visualprogrammingclass.boncal.helpers.statics
import com.visualprogrammingclass.boncal.models.article.ArrayListOfArticleResponse
import com.visualprogrammingclass.boncal.models.article.ArticleResponseItem
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
    ): ViewModel() {

    private val _token: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    private val _airQualityWidget: MutableLiveData<String> by lazy { MutableLiveData<String>("") }
    val airQualityWidget: LiveData<String> get()=_airQualityWidget

    private val _articles: MutableLiveData<ArrayList<ArticleResponseItem>?> by lazy { MutableLiveData<ArrayList<ArticleResponseItem>?>() }
    val articles: LiveData<ArrayList<ArticleResponseItem>?> get()=_articles

    init{
        getUserToken().invokeOnCompletion {
            Log.d("HomeVM", "_token when complete is now ${_token.value.toString()}")
        }
        Log.d("HomeVM", "_token fresh is now ${_token.value.toString()}")

        getLatestAirQualityWidgetData()
    }

    // Air Quality Widget
    // =============
    fun getArticles() = viewModelScope.launch{
        endRepository.getArticleData().let { arraylistofArticlesResponse ->
            if(arraylistofArticlesResponse.isSuccessful){

                arraylistofArticlesResponse.body()?.let { arrayListOfArticles ->

                   withContext(Dispatchers.Main){
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

    fun getLatestAirQualityWidgetData() = viewModelScope.launch {

            endRepository.getWidgetData(_token.value.toString()).let{ widgetResponse ->
                Log.d("HomeVM", "Getting Widget data with token: ${_token.value.toString()}")
                if(widgetResponse.isSuccessful) {
                    widgetResponse.body()?.let { widgetContent ->

                        withContext(Dispatchers.Main){
                            _airQualityWidget.postValue(widgetContent.data[0].image)
                            Log.d("Widget", widgetContent.data[0].image)
                        }
                    }

                } else {
                    Log.d("Widget", "Data Retrieval Failed")

                }
            }

//        endRepository.getWidgetData(_token.value.toString()).let{ widgetResponse ->
//                Log.d("HomeVM", "Getting Widget data with token: ${_token.value.toString()}")
//                if(widgetResponse.isSuccessful) {
//                    widgetResponse.body()?.let { widgetContent ->
//
//                        withContext(Dispatchers.Main){
//                            _airQualityWidget.postValue(widgetContent.data.image)
//                            Log.d("Widget", widgetContent.data.image)
//                        }
//                    }
//
//                } else {
//                    Log.d("Widget", "Data Retrieval Failed")
//
//                }
//            }
    }

    // Getting User Token
    // ===================

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

                // this doesn't work?
//                _token.postValue(token.toString())
//                Log.d("HomeVM", "_token.value is ${_token.value.toString()}")

            }

        }
    }


}