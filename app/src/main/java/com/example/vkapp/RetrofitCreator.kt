package com.example.vkapp

import com.example.vkapp.const.BASE_URL_OAUTH
import retrofit2.Retrofit

class RetrofitCreator {


    fun getUserAccessToken(): RetrofitService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_OAUTH)
            .build().create(RetrofitService::class.java)
        return retrofit
    }
}