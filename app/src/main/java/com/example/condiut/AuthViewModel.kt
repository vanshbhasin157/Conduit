package com.example.condiut

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.condiut.data.UserRepo
import kotlinx.coroutines.launch
import models.entities.User

class AuthViewModel : ViewModel() {

    private val _user = MutableLiveData<User>()

    val user: LiveData<User?> = _user

    fun login(email: String, password: String) = viewModelScope.launch {
        UserRepo.login(email, password)?.let {
            _user.postValue(it.user)
        }
    }

    fun signUp(username: String, email: String, password: String) = viewModelScope.launch {
            UserRepo.signup(username,email,password)?.let {
                _user.postValue(it)
            }
    }

    fun update(
        bio: String?,
        username: String?,
        email: String?,
        password: String?,
        imgUrl: String?

    )= viewModelScope.launch {
        UserRepo.update(bio,username,email,password,imgUrl)?.let {
            _user.postValue(it)
        }
    }

    fun getCurrentUser(token:String) = viewModelScope.launch {
        UserRepo.getCurrentUser(token)?.let {
            _user.postValue(it)
        }
    }

    fun logout(){
        _user.postValue(null)
    }
}
