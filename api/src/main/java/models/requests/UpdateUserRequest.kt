package models.requests


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import models.User

@JsonClass(generateAdapter = true)
data class UpdateUserRequest(
    @Json(name = "user")
    val user: User
)