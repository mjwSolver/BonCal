package com.visualprogrammingclass.boncal.models.widgets

import com.visualprogrammingclass.boncal.models.ApiResponse

typealias ArrayListOfWidgetResponse = ApiResponse<ArrayList<WidgetResponseItem>>

data class WidgetResponseItem(
    val CreatedAt: String,
    val DeletedAt: Any,
    val ID: Int,
    val UpdatedAt: String,
    val `data`: String,
    val image: String,
    val name: String,
    val supported_regions: Any,
    val title: String,
    val type: String
)