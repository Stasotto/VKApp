package com.example.vkapp

import androidx.lifecycle.ViewModel
import com.example.vkapp.model.ModelMain
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("authorize?client_id=8062067&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends,wall,&response_type=token&v=5.131")
    fun retrofit() : Call<ModelMain>
}