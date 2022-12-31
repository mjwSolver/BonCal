package com.visualprogrammingclass.boncal.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visualprogrammingclass.boncal.repositories.DataStoreRepository
import com.visualprogrammingclass.boncal.views.onboarding.OnBoardingPage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(private val repository:DataStoreRepository): ViewModel() {

    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )

    // Using Data Store - or store the file separately again
    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveOnBoardingState(completed = completed)
            Log.d("onBoarding", "welcomeViewModel saves $completed")
        }
    }
}