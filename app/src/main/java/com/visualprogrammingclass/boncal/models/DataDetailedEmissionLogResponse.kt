package com.visualprogrammingclass.boncal.models

import com.visualprogrammingclass.boncal.models.authentication.User

data class DataDetailedEmissionLogResponse(
    val Amount: Int,
    val CarbonOutput: Double,
    val CreatedAt: String,
    val DeletedAt: Any,
    val EmissionType: EmissionType,
    val EmissionTypeID: Int,
    val ID: Int,
    val UpdatedAt: String,
    val User: User,
    val UserID: Int
)