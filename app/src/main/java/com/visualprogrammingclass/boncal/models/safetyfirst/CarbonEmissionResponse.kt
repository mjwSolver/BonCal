package com.visualprogrammingclass.boncal.models.safetyfirst

import com.visualprogrammingclass.boncal.models.SingleAvailableEmissionType
import com.visualprogrammingclass.boncal.models.authentication.User

// API -> Kotlin
data class CarbonEmissionResponse(
    val ID: Int,
    val CreatedAt: String,
    val DeletedAt: Any,
    val UpdatedAt: String,
    val EmissionType: SingleAvailableEmissionType, // Hati hati
    val EmissionTypeID: Int,
    val User: User, // Hati Hati
    val UserID: Int,
    val Amount: Int,
    val CarbonOutput: Double,
)