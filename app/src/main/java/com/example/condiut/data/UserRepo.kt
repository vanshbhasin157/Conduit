package com.example.condiut.data

import com.example.api.CondiutClient
import models.entities.LoginData
import models.entities.User
import models.entities.UserCreds
import models.requests.LoginRequest
import models.requests.SignUPRequest
import models.requests.UpdateUserRequest
import models.responses.userResponse

object UserRepo {
    val api = CondiutClient.api
    val authApi = CondiutClient.authApi

    suspend fun login(email:String, password:String): userResponse? {
        val resp = api.loginUser(LoginRequest(LoginData(email,password)))
        CondiutClient.authToken = resp.body()?.user?.token
        return resp.body()
    }

    suspend fun getCurrentUser(token:String): User? {
        CondiutClient.authToken = token
        return authApi.getCurrentUser().body()?.user
    }



    suspend fun signup(username:String, email: String,password: String): User? {
        val resp = api.registerUser(SignUPRequest(UserCreds(email,password,username)))
        CondiutClient.authToken = resp.body()?.user?.token
        return resp?.body()?.user
    }

    suspend fun update(
        bio: String?,
        username: String?,
        email: String?,
        password: String?,
        imgUrl: String?
    ): User? {
        val resp = authApi.updateCurrentUser(UpdateUserRequest(models.User(bio,email,imgUrl,username,password)))
        return resp.body()?.user
    }



    suspend fun getUserProfile() = authApi.getCurrentUser().body()?.user


}