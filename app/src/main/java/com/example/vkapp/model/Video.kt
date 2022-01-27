package com.example.vkapp.model

data class Video(
    val access_key: String,
    val can_add: Int,
    val can_add_to_faves: Int,
    val can_comment: Int,
    val can_like: Int,
    val can_repost: Int,
    val can_subscribe: Int,
    val comments: Int,
    val date: Int,
    val description: String,
    val duration: Int,
    val first_frame: List<FirstFrame>,
    val height: Int,
    val id: Int,
    val image: List<ImageX>,
    val is_favorite: Boolean,
    val local_views: Int,
    val owner_id: Int,
    val platform: String,
    val title: String,
    val track_code: String,
    val type: String,
    val views: Int,
    val width: Int
)