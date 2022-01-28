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
    var retrofitBuilder = RetrofitCreator().getUserAccessToken()

    val token = MutableLiveData<String>()

    //Если вызывать из класса фрагмента, то выдает значение "k" как null
    // см. комменты в AuthorizationFragment
    fun retrofitResponse() {
        viewModelScope.launch {

            val response = retrofitBuilder.newsJSONResponse(
                getToken,
                VERSION
            ).awaitResponse()

            if (response.isSuccessful) {
                val data = response.body()
                val k = (data?.response?.items?.get(1)?.post_type).toString()
                Log.d("CWW", k)
            }
        }
    }

}