package models.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import models.entities.Article

@JsonClass(generateAdapter = true)
data class articleResponse(
    @Json(name = "article")
    val article: Article
)