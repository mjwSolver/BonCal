package com.visualprogrammingclass.boncal.models.widgets

// API -> Kotlin
data class WidgetResponse(
    val CreatedAt: String,
    val DeletedAt: Any,
    val ID: Int,
    val UpdatedAt: String,
    val `data`: String,
    val image: String,
    val name: String,
    val supported_regions: List<SupportedRegion>,
    val title: String,
    val type: String
)

// types
// image url ...?