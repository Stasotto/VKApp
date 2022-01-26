package com.example.vkapp

import retrofit2.http.GET

interface RetrofitService {

    @GET("authorize?client_id=8062067&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends,wall,&response_type=token&v=5.131")
    fun retrofit()
}