package com.visualprogrammingclass.boncal.viewModels

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

    fun getEmissionTypes(token:String) = viewModelScope.launch {
        endRepository.getEmissionTypes(token)
    }



}