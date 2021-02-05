package models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class articleResponse(
    @Json(name = "article")
    val article: Article
)