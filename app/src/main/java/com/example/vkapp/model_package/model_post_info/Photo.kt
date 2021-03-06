package com.example.vkapp.model_package.model_post_info

data class Photo(
    val access_key: String,
    val album_id: Int,
    val date: Int,
    val has_tags: Boolean,
    val id: Int,
    val owner_id: Int,
    val sizes: List<Size>,
    val text: String,
    val user_id: Int
)