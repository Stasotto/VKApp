package com.example.vkapp.model

data class Attachment(
    val photo: Photo,
    val poll: Poll,
    val type: String,
    val video: Video
)