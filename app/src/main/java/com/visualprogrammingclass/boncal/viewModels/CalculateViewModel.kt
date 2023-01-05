package com.visualprogrammingclass.boncal.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.visualprogrammingclass.boncal.helpers.statics
import com.visualprogrammingclass.boncal.models.CarbonEmissionDetail
import com.visualprogrammingclass.boncal.repositories.EndPointRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculateViewModel  @Inject constructor(
    private val endRepository: EndPointRepository
): ViewModel() {

    fun sendingCarbonEmissionData(carbonEmissionDetail: CarbonEmissionDetail) = viewModelScope.launch {
        endRepository.sendCarbonEmissionData(statics.token, carbonEmissionDetail)
    }

}