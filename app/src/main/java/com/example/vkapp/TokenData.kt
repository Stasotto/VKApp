package com.example.vkapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkapp.const.VERSION
import com.example.vkapp.const.getToken
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class TokenData : ViewModel() {
    val token = MutableLiveData<String>()


}