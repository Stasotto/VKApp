package com.example.vkapp

import com.example.vkapp.model.ModelMain
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("newsfeed.get") // При вызове @Query знак "?" после метода @GET добавляется автоматически
    fun newsJSONResponse(
        @Query("access_token") token: String, //а вот здесь после каждого вызова добавляется "&"
        @Query("v") version: String // т.е. таким образом мы прописываем фул ссылку, а уже значения можем сами втыкать
    ): Call<ModelMain>

    // https://api.vk.com/method/newsfeed.get?access_token=TOKEN&v=5.131  THIS IS FULL LINK


}