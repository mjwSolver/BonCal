package com.visualprogrammingclass.boncal.models.widgets

// API -> Kotlin
data class WidgetResponse(
    val CreatedAt: String,
    val DeletedAt: Any,
    val ID: Int,
    val UpdatedAt: String,
    val `data`: String,                 // Chrome Custom Tab
    val image: String,                  // It's fill can be Text or URL
    val name: String,
    val supported_regions: List<SupportedRegion>,
    val title: String,
    val type: WidgetType                    // Type of Image can be Text or URL
)

// Data Ngikut Type

// types
// image url ...?