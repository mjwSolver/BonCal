package com.visualprogrammingclass.boncal.viewModels

import androidx.lifecycle.ViewModel
import com.visualprogrammingclass.boncal.repositories.EndPointRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalculateViewModel  @Inject constructor(
    private val endRepository: EndPointRepository
): ViewModel() {
    
}