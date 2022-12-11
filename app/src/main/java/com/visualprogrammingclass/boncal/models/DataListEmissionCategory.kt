package com.visualprogrammingclass.boncal.models

data class DataListEmissionCategory(
    val CreatedAt: String,
    val DeletedAt: Any,
    val Description: String,
    val EmissionFactor: Double,
    val ID: Int,
    val Name: String,
    val Source: String,
    val Unit: String,
    val UpdatedAt: String,
    val YearReleased: Int
)