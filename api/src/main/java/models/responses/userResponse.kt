package models.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import models.entities.User

@JsonClass(generateAdapter = true)
data class userResponse(
    @Json(name = "user")
    val user: User
)