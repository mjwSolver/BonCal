package com.visualprogrammingclass.boncal.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visualprogrammingclass.boncal.repositories.EndPointRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val endRepository: EndPointRepository
): ViewModel() {

    // Jadi nanti kan dalam emission types ada id, we take that ID and associate it to an on click inside
    // this eliminates the need to make anymore.

    fun getEmissionTypes(token:String) = viewModelScope.launch {
        endRepository.getEmissionTypes(token)
    }



}