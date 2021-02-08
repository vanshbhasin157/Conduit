package com.example.condiut.data

import com.example.api.CondiutClient
import models.entities.LoginData
import models.requests.LoginRequest
import models.responses.userResponse

object UserRepo {
    val api = CondiutClient().api

    suspend fun login(email:String, password:String): userResponse? {
        val resp = api.loginUser(LoginRequest(LoginData(email,password)))
        return resp.body()
    }
}