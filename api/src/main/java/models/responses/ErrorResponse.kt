package models.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import models.entities.Errors

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "errors")
    val errors: Errors
)