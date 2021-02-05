package models.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import models.entities.Article

@JsonClass(generateAdapter = true)
data class MultipleArticleResponse(
    @Json(name = "articles")
    val articles: List<Article>,
    @Json(name = "articlesCount")
    val articlesCount: Int
)