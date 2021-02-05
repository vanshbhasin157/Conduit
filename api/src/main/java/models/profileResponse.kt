package models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class profileResponse(
    @Json(name = "profile")
    val profile: Profile
)