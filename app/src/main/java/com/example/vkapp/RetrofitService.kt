package com.example.vkapp

import com.example.vkapp.model.ModelMain
import com.example.vkapp.model_user_photo_and_name.ModelMainUserPhotoAndName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("wall.get") // При вызове @Query знак "?" после метода @GET добавляется автоматически
    fun newsJSONResponse(
        @Query("count") count: String,
        @Query("access_token") token: String, //а вот здесь после каждого вызова добавляется "&"
        @Query("v") version: String // т.е. таким образом мы прописываем фул ссылку, а уже значения можем сами втыкать
    ): Call<ModelMain>

    // https://api.vk.com/method/wall.get?access_token=TOKEN&v=5.131

    @GET("users.get")
    fun getPhoto(
        @Query("user_ids") user_ids: String,
        @Query("fields") fields: String,
        @Query("access_token") token: String,
        @Query("v") version: String
    ): Call<ModelMainUserPhotoAndName>


    //https://api.vk.com/method/users.get?user_ids=USERID&fields=photo_100&access_token=TOKEN&v=5.131

}