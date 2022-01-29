package com.example.vkapp

import java.util.*

//класс для адаптера в NewFragment

data class NewsToAdapt(
    val avatarRes: Int,
    val authorName: String,
    val newsDate: Date,
    val newsText: String
)
