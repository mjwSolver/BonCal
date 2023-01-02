package com.visualprogrammingclass.boncal.models

data class SingleAvailableEmissionType(
    val BackgroundColor: String,
    val CreatedAt: String,
    val DataSource: String,
    val DataSourceUrl: String,
    val DeletedAt: Any,
    val Description: String,
    val EmissionFactor: Double,
    val ForegroundColor: String,
    val ID: Int,
    val Icon: String,
    val Name: String,
    val Unit: String,
    val UpdatedAt: String,
    val YearReleased: Int
)