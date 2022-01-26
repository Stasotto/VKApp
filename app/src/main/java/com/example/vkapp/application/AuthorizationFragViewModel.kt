package com.example.vkapp.application

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkapp.domain.usecase.AuthorizeUserUseCase
import kotlinx.coroutines.launch

class AuthorizationFragViewModel(private val authorizeUserUseCase: AuthorizeUserUseCase) :
    ViewModel() {


    private val _authorizationResult = MutableLiveData<Boolean>()
    val authorizationResult: LiveData<Boolean> get() = _authorizationResult

    fun authorize() {
        viewModelScope.launch {
            _authorizationResult.value = authorizeUserUseCase.execute()
        }

    }
}

