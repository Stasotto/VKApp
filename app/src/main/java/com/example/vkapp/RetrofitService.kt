package com.example.vkapp

import com.example.vkapp.const.VERSION
import com.example.vkapp.model.ModelMain
import com.example.vkapp.model_add_like.ModelAddDeleteLike
import com.example.vkapp.model_user_photo_and_name.ModelMainUserPhotoAndName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("wall.get")
    fun newsJSONResponse(
        @Query("count") count: String,
        @Query("access_token") token: String,
        @Query("v") version: String
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


    @GET("wall.addLike")
    fun addLike(
        @Query("post_id") post_id: String,
        @Query("access_token") token: String,
        @Query("v") v: String = VERSION
    ): Call<ModelAddDeleteLike>

    // https://api.vk.com/method/wall.addLike?post_id=POSTID&access_token=TOKEN&v=5.131


    @GET("wall.deleteLike")
    fun deleteLike(
        @Query("post_id") post_id: String,
        @Query("access_token") token: String,
        @Query("v") v: String = VERSION
    ): Call<ModelAddDeleteLike>
    // https://api.vk.com/method/wall.deleteLike?&post_id=POSTID&access_token=TOKEN&v=5.131

}