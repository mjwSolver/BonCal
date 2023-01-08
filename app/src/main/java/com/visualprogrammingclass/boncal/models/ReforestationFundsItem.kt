package com.visualprogrammingclass.boncal.models

typealias ReforestationListResponse = ApiResponse<ReforestationList>
typealias ReforestationList = ArrayList<ReforestationFundsItem>

data class ReforestationFundsItem(
    val CreatedAt: String,
    val DeletedAt: Any,
    val Description: String,
    val ID: Int,
    val Logo: String,
    val Name: String,
    val UpdatedAt: String,
    val Url: String
)