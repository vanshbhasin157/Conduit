package models.requests


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import models.entities.UserCreds

@JsonClass(generateAdapter = true)
data class SignUPRequest(
    @Json(name = "user")
    val user: UserCreds
)