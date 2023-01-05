package com.visualprogrammingclass.boncal.models.article

import com.visualprogrammingclass.boncal.models.ApiResponse

typealias ArrayListOfArticleResponse = ApiResponse<ArrayList<ArticleResponseItem>>

data class ArticleResponseItem(
    val CoverImage: String,
    val CreatedAt: String,
    val DeletedAt: Any,
    val ID: Int,
    val Source: String,
    val Title: String,
    val UpdatedAt: String,
    val Url: String
)