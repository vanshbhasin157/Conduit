package models.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import models.entities.Profile

@JsonClass(generateAdapter = true)
data class profileResponse(
    @Json(name = "profile")
    val profile: Profile
)