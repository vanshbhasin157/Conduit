package models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class userResponse(
    @Json(name = "user")
    val user: User
)