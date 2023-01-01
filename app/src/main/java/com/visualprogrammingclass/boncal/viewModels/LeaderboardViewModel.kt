package com.visualprogrammingclass.boncal.viewModels

import androidx.lifecycle.ViewModel
import com.visualprogrammingclass.boncal.repositories.DataStoreRepository
import com.visualprogrammingclass.boncal.repositories.EndPointRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewModel @Inject constructor(
    private val endPointRepository: EndPointRepository,
    private val dataRepository: DataStoreRepository
): ViewModel() {

}